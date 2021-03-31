/**
 *    页面无刷新上传文件
 *    修改版(支持多文件批量上传)
 */
jQuery.extend({
    createUploadIframe: function(id, uri) {
        var frameId = 'jUploadFrame' + id;
        var iframeHtml = '<iframe id="' + frameId + '" name="' + frameId + '" style="position:absolute; top:-9999px; left:-9999px"';
        if (window.ActiveXObject) {
            if (typeof uri == 'boolean') {
                iframeHtml += ' src="' + 'javascript:false' + '"';
            }
            else if (typeof uri == 'string') {
                iframeHtml += ' src="' + uri + '"';
            }
        }
        iframeHtml += ' />';
        jQuery(iframeHtml).appendTo(document.body);
        return jQuery('#' + frameId).get(0);
    },
    createUploadForm: function(id, files, data, fileArr, action) {
        var formId = 'jUploadForm' + id;
        var fileId = 'jUploadFile' + id;
        action = action || '';
        var form = jQuery('<form action="' + action + '" method="POST" name="' + formId + '" id="' + formId + '" enctype="multipart/form-data"></form>');
        if (data) {
            for (var i in data) {
                var ele = document.createElement("input");
                ele.type = "hidden";
                ele.name = i;
                ele.value = data[i];
                $(ele).appendTo(form);
                //jQuery('<input type="hidden" name="' + i + '" value="' + data[i] + '" />').appendTo(form);
            }
        }
        //edit by huangxiaoyun  lexun.
        if (files) {
            files.each(function() {
                var oldElement = jQuery(this);
                var newElement = jQuery(oldElement).clone(true);
                jQuery(oldElement).attr('id', fileId);
                jQuery(oldElement).before(newElement);
                jQuery(oldElement).appendTo(form);
                //将新文件上传框和旧文件上传框组成一个对象添加到对象数组
                fileArr.push({ tmpfile: newElement[0], orifile: oldElement[0] });
            });
        }
        jQuery(form).css('position', 'absolute');
        jQuery(form).css('top', '-1200px');
        jQuery(form).css('left', '-1200px');
        jQuery(form).appendTo('body');
        return form;
    },
    ajaxFileUpload: function(s) {
        s = jQuery.extend({ secureuri: false, dataType: 'json' }, jQuery.ajaxSettings, s);
        var id = new Date().getTime();
        //一个对象数组，对象有两个属性，一个表示临时input文件上传框，一个表示原始文件上传框
        var fileArr = [];
        var frameId = 'jUploadFrame' + id;
        var formId = 'jUploadForm' + id;
        var form = jQuery.createUploadForm(id, s.files, (typeof (s.data) == 'undefined' ? false : s.data), fileArr, s.url);
        var io = jQuery.createUploadIframe(id, s.secureuri);
        if (s.global && !jQuery.active++) {
            jQuery.event.trigger("ajaxStart");
        }
        var requestDone = false;
        var xml = {}
        if (s.global)
            jQuery.event.trigger("ajaxSend", [xml, s]);
        var uploadCallback = function(isTimeout) {
            var io = document.getElementById(frameId);
            try {
                if (io.contentWindow) {
                    xml.responseText = io.contentWindow.document.body ? io.contentWindow.document.body.innerHTML : null;
                    xml.responseXML = io.contentWindow.document.XMLDocument ? io.contentWindow.document.XMLDocument : io.contentWindow.document;
                } else if (io.contentDocument) {
                    xml.responseText = io.contentDocument.document.body ? io.contentDocument.document.body.innerHTML : null;
                    xml.responseXML = io.contentDocument.document.XMLDocument ? io.contentDocument.document.XMLDocument : io.contentDocument.document;
                }
            } catch (e) {
                jQuery.handleError && jQuery.handleError(s, xml, null, e);
            }
            if (xml || isTimeout == "timeout") {
                requestDone = true;
                var status;
                try {
                    status = isTimeout != "timeout" ? "success" : "error";
                    if (status != "error") {
                        var data = jQuery.uploadHttpData(xml, s.dataType);
                        if (s.success)
                            s.success(data, status);
                        if (s.global)
                            jQuery.event.trigger("ajaxSuccess", [xml, s]);
                    } else
                        jQuery.handleError && jQuery.handleError(s, xml, status);
                } catch (e) {
                    status = "error";
                    jQuery.handleError && jQuery.handleError(s, xml, status, e);
                }
                if (s.global)
                    jQuery.event.trigger("ajaxComplete", [xml, s]);
                if (s.global && ! --jQuery.active)
                    jQuery.event.trigger("ajaxStop");
                if (s.complete)
                    s.complete(xml, status);
                jQuery(io).unbind();
                setTimeout(function() {
                    try {
                        jQuery(io).remove();
                        jQuery(form).remove();
                    } catch (e) {
                        jQuery.handleError && jQuery.handleError(s, xml, null, e);
                    }
                }, 100);
                xml = null;
            }
        }
        if (s.timeout > 0) {
            setTimeout(function() {
                if (!requestDone) uploadCallback("timeout");
            }, s.timeout);
        }
        try {
            var form = $('#' + formId);
            //ie8修改action报错.?
            try { form.attr('action', s.url); } catch (e) { }
            form.attr('method', 'POST');
            form.attr('target', frameId);
            if (form.attr('encoding')) {
                jQuery(form).attr('encoding', 'multipart/form-data');
            }
            else {
                jQuery(form).attr('enctype', 'multipart/form-data');
            }

            jQuery(form).submit();
            //表单提交以后，将旧的文件上传框恢复到原始位置，用以保留已经上传的文件
            for (var i = 0; i < fileArr.length; i++) {
                fileArr[i].tmpfile.parentNode.replaceChild(fileArr[i].orifile, fileArr[i].tmpfile);
            }
        } catch (e) {
            jQuery.handleError && jQuery.handleError(s, xml, null, e);
        }
        jQuery('#' + frameId).load(uploadCallback);
        return { abort: function() { } };
    },
    uploadHttpData: function(r, type) {
        var data = !type;
        data = type == "xml" || data ? r.responseXML : r.responseText;
        if (type == "script")
            jQuery.globalEval(data);
        if (type == "json")
            eval("data = " + data);
        if (type == "html")
            jQuery("<div>").html(data).evalScripts();
        return data;
    }
});
