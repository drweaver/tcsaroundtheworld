(function () {
    var h, aa = aa || {}, l = this,
        ba = function (a) {
            a = a.split(".");
            for (var b = l, c; c = a.shift();)
                if (null != b[c]) b = b[c];
                else return null;
            return b
        }, ca = function () {}, da = function (a) {
            var b = typeof a;
            if ("object" == b)
                if (a) {
                    if (a instanceof Array) return "array";
                    if (a instanceof Object) return b;
                    var c = Object.prototype.toString.call(a);
                    if ("[object Window]" == c) return "object";
                    if ("[object Array]" == c || "number" == typeof a.length && "undefined" != typeof a.splice && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("splice")) return "array";
                    if ("[object Function]" == c || "undefined" != typeof a.call && "undefined" != typeof a.propertyIsEnumerable && !a.propertyIsEnumerable("call")) return "function"
                } else return "null";
                else if ("function" == b && "undefined" == typeof a.call) return "object";
            return b
        }, m = function (a) {
            return "array" == da(a)
        }, ea = function (a) {
            var b = da(a);
            return "array" == b || "object" == b && "number" == typeof a.length
        }, n = function (a) {
            return "string" == typeof a
        }, fa = function (a) {
            return "function" == da(a)
        }, ga = function (a) {
            var b = typeof a;
            return "object" == b && null != a || "function" ==
                b
        }, ha = function (a, b, c) {
            return a.call.apply(a.bind, arguments)
        }, ia = function (a, b, c) {
            if (!a) throw Error();
            if (2 < arguments.length) {
                var d = Array.prototype.slice.call(arguments, 2);
                return function () {
                    var c = Array.prototype.slice.call(arguments);
                    Array.prototype.unshift.apply(c, d);
                    return a.apply(b, c)
                }
            }
            return function () {
                return a.apply(b, arguments)
            }
        }, p = function (a, b, c) {
            p = Function.prototype.bind && -1 != Function.prototype.bind.toString().indexOf("native code") ? ha : ia;
            return p.apply(null, arguments)
        }, ja = Date.now || function () {
            return +new Date
        },
        q = function (a, b) {
            var c = a.split("."),
                d = l;
            c[0] in d || !d.execScript || d.execScript("var " + c[0]);
            for (var e; c.length && (e = c.shift());) c.length || void 0 === b ? d = d[e] ? d[e] : d[e] = {} : d[e] = b
        }, r = function (a, b) {
            function c() {}
            c.prototype = b.prototype;
            a.superClass_ = b.prototype;
            a.prototype = new c;
            a.base = function (a, c, g) {
                return b.prototype[c].apply(a, Array.prototype.slice.call(arguments, 2))
            }
        };
    Function.prototype.bind = Function.prototype.bind || function (a, b) {
        if (1 < arguments.length) {
            var c = Array.prototype.slice.call(arguments, 1);
            c.unshift(this, a);
            return p.apply(null, c)
        }
        return p(this, a)
    };
    var s = {};
    q("RecaptchaTemplates", s);
    s.VertHtml = '<table id="recaptcha_table" class="recaptchatable" > <tr> <td colspan="6" class=\'recaptcha_r1_c1\'></td> </tr> <tr> <td class=\'recaptcha_r2_c1\'></td> <td colspan="4" class=\'recaptcha_image_cell\'><center><div id="recaptcha_image"></div></center></td> <td class=\'recaptcha_r2_c2\'></td> </tr> <tr> <td rowspan="6" class=\'recaptcha_r3_c1\'></td> <td colspan="4" class=\'recaptcha_r3_c2\'></td> <td rowspan="6" class=\'recaptcha_r3_c3\'></td> </tr> <tr> <td rowspan="3" class=\'recaptcha_r4_c1\' height="49"> <div class="recaptcha_input_area"> <input name="recaptcha_response_field" id="recaptcha_response_field" type="text" autocorrect="off" autocapitalize="off" placeholder="" /> <span id="recaptcha_privacy" class="recaptcha_only_if_privacy"></span> </div> </td> <td rowspan="4" class=\'recaptcha_r4_c2\'></td> <td><a id=\'recaptcha_reload_btn\'><img id=\'recaptcha_reload\' width="25" height="17" /></a></td> <td rowspan="4" class=\'recaptcha_r4_c4\'></td> </tr> <tr> <td><a id=\'recaptcha_switch_audio_btn\' class="recaptcha_only_if_image"><img id=\'recaptcha_switch_audio\' width="25" height="16" alt="" /></a><a id=\'recaptcha_switch_img_btn\' class="recaptcha_only_if_audio"><img id=\'recaptcha_switch_img\' width="25" height="16" alt=""/></a></td> </tr> <tr> <td><a id=\'recaptcha_whatsthis_btn\'><img id=\'recaptcha_whatsthis\' width="25" height="16" /></a></td> </tr> <tr> <td class=\'recaptcha_r7_c1\'></td> <td class=\'recaptcha_r8_c1\'></td> </tr> </table> ';
    s.CleanCss = ".recaptchatable td img{display:block}.recaptchatable .recaptcha_image_cell center img{height:57px}.recaptchatable .recaptcha_image_cell center{height:57px}.recaptchatable .recaptcha_image_cell{background-color:white;height:57px;padding:7px!important}.recaptchatable,#recaptcha_area tr,#recaptcha_area td,#recaptcha_area th{margin:0!important;border:0!important;border-collapse:collapse!important;vertical-align:middle!important}.recaptchatable *{margin:0;padding:0;border:0;color:black;position:static;top:auto;left:auto;right:auto;bottom:auto}.recaptchatable #recaptcha_image{position:relative;margin:auto;border:1px solid #dfdfdf!important}.recaptchatable #recaptcha_image #recaptcha_challenge_image{display:block}.recaptchatable #recaptcha_image #recaptcha_ad_image{display:block;position:absolute;top:0}.recaptchatable a img{border:0}.recaptchatable a,.recaptchatable a:hover{cursor:pointer;outline:none;border:0!important;padding:0!important;text-decoration:none;color:blue;background:none!important;font-weight:normal}.recaptcha_input_area{position:relative!important;background:none!important}.recaptchatable label.recaptcha_input_area_text{border:1px solid #dfdfdf!important;margin:0!important;padding:0!important;position:static!important;top:auto!important;left:auto!important;right:auto!important;bottom:auto!important}.recaptcha_theme_red label.recaptcha_input_area_text,.recaptcha_theme_white label.recaptcha_input_area_text{color:black!important}.recaptcha_theme_blackglass label.recaptcha_input_area_text{color:white!important}.recaptchatable #recaptcha_response_field{font-size:11pt}.recaptcha_theme_blackglass #recaptcha_response_field,.recaptcha_theme_white #recaptcha_response_field{border:1px solid gray}.recaptcha_theme_red #recaptcha_response_field{border:1px solid #cca940}.recaptcha_audio_cant_hear_link{font-size:7pt;color:black}.recaptchatable{line-height:1em;border:1px solid #dfdfdf!important}.recaptcha_error_text{color:red}.recaptcha_only_if_privacy{float:right;text-align:right;margin-right:7px}#recaptcha-ad-choices{position:absolute;height:15px;top:0;right:0}#recaptcha-ad-choices img{height:15px}.recaptcha-ad-choices-collapsed{width:15px;height:15px;display:block}.recaptcha-ad-choices-expanded{width:75px;height:15px;display:none}#recaptcha-ad-choices:hover .recaptcha-ad-choices-collapsed{display:none}#recaptcha-ad-choices:hover .recaptcha-ad-choices-expanded{display:block}";
    s.CleanHtml = '<table id="recaptcha_table" class="recaptchatable"> <tr height="73"> <td class=\'recaptcha_image_cell\' width="302"><center><div id="recaptcha_image"></div></center></td> <td style="padding: 10px 7px 7px 7px;"> <a id=\'recaptcha_reload_btn\'><img id=\'recaptcha_reload\' width="25" height="18" alt="" /></a> <a id=\'recaptcha_switch_audio_btn\' class="recaptcha_only_if_image"><img id=\'recaptcha_switch_audio\' width="25" height="15" alt="" /></a><a id=\'recaptcha_switch_img_btn\' class="recaptcha_only_if_audio"><img id=\'recaptcha_switch_img\' width="25" height="15" alt=""/></a> <a id=\'recaptcha_whatsthis_btn\'><img id=\'recaptcha_whatsthis\' width="25" height="16" /></a> </td> <td style="padding: 18px 7px 18px 7px;"> <img id=\'recaptcha_logo\' alt="" width="71" height="36" /> </td> </tr> <tr> <td style="padding-left: 7px;"> <div class="recaptcha_input_area" style="padding-top: 2px; padding-bottom: 7px;"> <input style="border: 1px solid #3c3c3c; width: 302px;" name="recaptcha_response_field" id="recaptcha_response_field" type="text" /> </div> </td> <td colspan=2><span id="recaptcha_privacy" class="recaptcha_only_if_privacy"></span></td> </tr> </table> ';
    s.VertCss = ".recaptchatable td img{display:block}.recaptchatable .recaptcha_r1_c1{background:url('IMGROOT/sprite.png') 0 -63px no-repeat;width:318px;height:9px}.recaptchatable .recaptcha_r2_c1{background:url('IMGROOT/sprite.png') -18px 0 no-repeat;width:9px;height:57px}.recaptchatable .recaptcha_r2_c2{background:url('IMGROOT/sprite.png') -27px 0 no-repeat;width:9px;height:57px}.recaptchatable .recaptcha_r3_c1{background:url('IMGROOT/sprite.png') 0 0 no-repeat;width:9px;height:63px}.recaptchatable .recaptcha_r3_c2{background:url('IMGROOT/sprite.png') -18px -57px no-repeat;width:300px;height:6px}.recaptchatable .recaptcha_r3_c3{background:url('IMGROOT/sprite.png') -9px 0 no-repeat;width:9px;height:63px}.recaptchatable .recaptcha_r4_c1{background:url('IMGROOT/sprite.png') -43px 0 no-repeat;width:171px;height:49px}.recaptchatable .recaptcha_r4_c2{background:url('IMGROOT/sprite.png') -36px 0 no-repeat;width:7px;height:57px}.recaptchatable .recaptcha_r4_c4{background:url('IMGROOT/sprite.png') -214px 0 no-repeat;width:97px;height:57px}.recaptchatable .recaptcha_r7_c1{background:url('IMGROOT/sprite.png') -43px -49px no-repeat;width:171px;height:8px}.recaptchatable .recaptcha_r8_c1{background:url('IMGROOT/sprite.png') -43px -49px no-repeat;width:25px;height:8px}.recaptchatable .recaptcha_image_cell center img{height:57px}.recaptchatable .recaptcha_image_cell center{height:57px}.recaptchatable .recaptcha_image_cell{background-color:white;height:57px}#recaptcha_area,#recaptcha_table{width:318px!important}.recaptchatable,#recaptcha_area tr,#recaptcha_area td,#recaptcha_area th{margin:0!important;border:0!important;padding:0!important;border-collapse:collapse!important;vertical-align:middle!important}.recaptchatable *{margin:0;padding:0;border:0;font-family:helvetica,sans-serif;font-size:8pt;color:black;position:static;top:auto;left:auto;right:auto;bottom:auto}.recaptchatable #recaptcha_image{position:relative;margin:auto}.recaptchatable #recaptcha_image #recaptcha_challenge_image{display:block}.recaptchatable #recaptcha_image #recaptcha_ad_image{display:block;position:absolute;top:0}.recaptchatable img{border:0!important;margin:0!important;padding:0!important}.recaptchatable a,.recaptchatable a:hover{cursor:pointer;outline:none;border:0!important;padding:0!important;text-decoration:none;color:blue;background:none!important;font-weight:normal}.recaptcha_input_area{position:relative!important;width:153px!important;height:45px!important;margin-left:7px!important;margin-right:7px!important;background:none!important}.recaptchatable label.recaptcha_input_area_text{margin:0!important;padding:0!important;position:static!important;top:auto!important;left:auto!important;right:auto!important;bottom:auto!important;background:none!important;height:auto!important;width:auto!important}.recaptcha_theme_red label.recaptcha_input_area_text,.recaptcha_theme_white label.recaptcha_input_area_text{color:black!important}.recaptcha_theme_blackglass label.recaptcha_input_area_text{color:white!important}.recaptchatable #recaptcha_response_field{width:153px!important;position:relative!important;bottom:7px!important;padding:0!important;margin:15px 0 0 0!important;font-size:10pt}.recaptcha_theme_blackglass #recaptcha_response_field,.recaptcha_theme_white #recaptcha_response_field{border:1px solid gray}.recaptcha_theme_red #recaptcha_response_field{border:1px solid #cca940}.recaptcha_audio_cant_hear_link{font-size:7pt;color:black}.recaptchatable{line-height:1!important}#recaptcha_instructions_error{color:red!important}.recaptcha_only_if_privacy{float:right;text-align:right}#recaptcha-ad-choices{position:absolute;height:15px;top:0;right:0}#recaptcha-ad-choices img{height:15px}.recaptcha-ad-choices-collapsed{width:15px;height:15px;display:block}.recaptcha-ad-choices-expanded{width:75px;height:15px;display:none}#recaptcha-ad-choices:hover .recaptcha-ad-choices-collapsed{display:none}#recaptcha-ad-choices:hover .recaptcha-ad-choices-expanded{display:block}";
    var t = {
        visual_challenge: "Get a visual challenge",
        audio_challenge: "Get an audio challenge",
        refresh_btn: "Get a new challenge",
        instructions_visual: "Type the text:",
        instructions_audio: "Type what you hear:",
        help_btn: "Help",
        play_again: "Play sound again",
        cant_hear_this: "Download sound as MP3",
        incorrect_try_again: "Incorrect. Try again.",
        image_alt_text: "reCAPTCHA challenge image",
        privacy_and_terms: "Privacy & Terms"
    }, ka = {
            visual_challenge: "\u0627\u0644\u062d\u0635\u0648\u0644 \u0639\u0644\u0649 \u062a\u062d\u062f\u064d \u0645\u0631\u0626\u064a",
            audio_challenge: "\u0627\u0644\u062d\u0635\u0648\u0644 \u0639\u0644\u0649 \u062a\u062d\u062f\u064d \u0635\u0648\u062a\u064a",
            refresh_btn: "\u0627\u0644\u062d\u0635\u0648\u0644 \u0639\u0644\u0649 \u062a\u062d\u062f\u064d \u062c\u062f\u064a\u062f",
            instructions_visual: "\u064a\u0631\u062c\u0649 \u0643\u062a\u0627\u0628\u0629 \u0627\u0644\u0646\u0635:",
            instructions_audio: "\u0627\u0643\u062a\u0628 \u0645\u0627 \u062a\u0633\u0645\u0639\u0647:",
            help_btn: "\u0645\u0633\u0627\u0639\u062f\u0629",
            play_again: "\u062a\u0634\u063a\u064a\u0644 \u0627\u0644\u0635\u0648\u062a \u0645\u0631\u0629 \u0623\u062e\u0631\u0649",
            cant_hear_this: "\u062a\u0646\u0632\u064a\u0644 \u0627\u0644\u0635\u0648\u062a \u0628\u062a\u0646\u0633\u064a\u0642 MP3",
            incorrect_try_again: "\u063a\u064a\u0631 \u0635\u062d\u064a\u062d. \u0623\u0639\u062f \u0627\u0644\u0645\u062d\u0627\u0648\u0644\u0629.",
            image_alt_text: "\u0635\u0648\u0631\u0629 \u0627\u0644\u062a\u062d\u062f\u064a \u0645\u0646 reCAPTCHA",
            privacy_and_terms: "\u0627\u0644\u062e\u0635\u0648\u0635\u064a\u0629 \u0648\u0627\u0644\u0628\u0646\u0648\u062f"
        }, la = {
            visual_challenge: "Obtener una pista visual",
            audio_challenge: "Obtener una pista sonora",
            refresh_btn: "Obtener una pista nueva",
            instructions_visual: "Introduzca el texto:",
            instructions_audio: "Escribe lo que oigas:",
            help_btn: "Ayuda",
            play_again: "Volver a reproducir el sonido",
            cant_hear_this: "Descargar el sonido en MP3",
            incorrect_try_again: "Incorrecto. Vu\u00e9lvelo a intentar.",
            image_alt_text: "Pista de imagen reCAPTCHA",
            privacy_and_terms: "Privacidad y condiciones"
        }, ma = {
            visual_challenge: "Kumuha ng pagsubok na visual",
            audio_challenge: "Kumuha ng pagsubok na audio",
            refresh_btn: "Kumuha ng bagong pagsubok",
            instructions_visual: "I-type ang teksto:",
            instructions_audio: "I-type ang iyong narinig",
            help_btn: "Tulong",
            play_again: "I-play muli ang tunog",
            cant_hear_this: "I-download ang tunog bilang MP3",
            incorrect_try_again: "Hindi wasto. Muling subukan.",
            image_alt_text: "larawang panghamon ng reCAPTCHA",
            privacy_and_terms: "Privacy at Mga Tuntunin"
        }, na = {
            visual_challenge: "Test visuel",
            audio_challenge: "Test audio",
            refresh_btn: "Nouveau test",
            instructions_visual: "Saisissez le texte\u00a0:",
            instructions_audio: "Qu'entendez-vous ?",
            help_btn: "Aide",
            play_again: "R\u00e9\u00e9couter",
            cant_hear_this: "T\u00e9l\u00e9charger l'audio au format MP3",
            incorrect_try_again: "Incorrect. Veuillez r\u00e9essayer.",
            image_alt_text: "Image reCAPTCHA",
            privacy_and_terms: "Confidentialit\u00e9 et conditions d'utilisation"
        }, oa = {
            visual_challenge: "Dapatkan kata pengujian berbentuk visual",
            audio_challenge: "Dapatkan kata pengujian berbentuk audio",
            refresh_btn: "Dapatkan kata pengujian baru",
            instructions_visual: "Ketik teks:",
            instructions_audio: "Ketik yang Anda dengar:",
            help_btn: "Bantuan",
            play_again: "Putar suara sekali lagi",
            cant_hear_this: "Unduh suara sebagai MP3",
            incorrect_try_again: "Salah. Coba lagi.",
            image_alt_text: "Gambar tantangan reCAPTCHA",
            privacy_and_terms: "Privasi & Persyaratan"
        }, pa = {
            visual_challenge: "\u05e7\u05d1\u05dc \u05d0\u05ea\u05d2\u05e8 \u05d7\u05d6\u05d5\u05ea\u05d9",
            audio_challenge: "\u05e7\u05d1\u05dc \u05d0\u05ea\u05d2\u05e8 \u05e9\u05de\u05e2",
            refresh_btn: "\u05e7\u05d1\u05dc \u05d0\u05ea\u05d2\u05e8 \u05d7\u05d3\u05e9",
            instructions_visual: "\u05d4\u05e7\u05dc\u05d3 \u05d0\u05ea \u05d4\u05d8\u05e7\u05e1\u05d8:",
            instructions_audio: "\u05d4\u05e7\u05dc\u05d3 \u05d0\u05ea \u05de\u05d4 \u05e9\u05d0\u05ea\u05d4 \u05e9\u05d5\u05de\u05e2:",
            help_btn: "\u05e2\u05d6\u05e8\u05d4",
            play_again: "\u05d4\u05e4\u05e2\u05dc \u05e9\u05d5\u05d1 \u05d0\u05ea \u05d4\u05e9\u05de\u05e2",
            cant_hear_this: "\u05d4\u05d5\u05e8\u05d3 \u05e9\u05de\u05e2 \u05db-3MP",
            incorrect_try_again: "\u05e9\u05d2\u05d5\u05d9. \u05e0\u05e1\u05d4 \u05e9\u05d5\u05d1.",
            image_alt_text: "\u05ea\u05de\u05d5\u05e0\u05ea \u05d0\u05ea\u05d2\u05e8 \u05e9\u05dc reCAPTCHA",
            privacy_and_terms: "\u05e4\u05e8\u05d8\u05d9\u05d5\u05ea \u05d5\u05ea\u05e0\u05d0\u05d9\u05dd"
        }, qa = {
            visual_challenge: "Obter um desafio visual",
            audio_challenge: "Obter um desafio de \u00e1udio",
            refresh_btn: "Obter um novo desafio",
            instructions_visual: "Digite o texto:",
            instructions_audio: "Digite o que voc\u00ea ouve:",
            help_btn: "Ajuda",
            play_again: "Reproduzir som novamente",
            cant_hear_this: "Fazer download do som no formato MP3",
            incorrect_try_again: "Incorreto. Tente novamente.",
            image_alt_text: "Imagem de desafio reCAPTCHA",
            privacy_and_terms: "Privacidade e Termos"
        }, ra = {
            visual_challenge: "Ob\u0163ine\u0163i un cod captcha vizual",
            audio_challenge: "Ob\u0163ine\u0163i un cod captcha audio",
            refresh_btn: "Ob\u0163ine\u0163i un nou cod captcha",
            instructions_visual: "Introduce\u021bi textul:",
            instructions_audio: "Introduce\u0163i ceea ce auzi\u0163i:",
            help_btn: "Ajutor",
            play_again: "Reda\u0163i sunetul din nou",
            cant_hear_this: "Desc\u0103rca\u0163i fi\u015fierul audio ca MP3",
            incorrect_try_again: "Incorect. \u00cencerca\u0163i din nou.",
            image_alt_text: "Imagine de verificare reCAPTCHA",
            privacy_and_terms: "Confiden\u0163ialitate \u015fi termeni"
        }, sa = {
            visual_challenge: "\u6536\u5230\u4e00\u4e2a\u89c6\u9891\u9080\u8bf7",
            audio_challenge: "\u6362\u4e00\u7ec4\u97f3\u9891\u9a8c\u8bc1\u7801",
            refresh_btn: "\u6362\u4e00\u7ec4\u9a8c\u8bc1\u7801",
            instructions_visual: "\u8f93\u5165\u6587\u5b57\uff1a",
            instructions_audio: "\u8bf7\u952e\u5165\u60a8\u542c\u5230\u7684\u5185\u5bb9\uff1a",
            help_btn: "\u5e2e\u52a9",
            play_again: "\u91cd\u65b0\u64ad\u653e",
            cant_hear_this: "\u4ee5 MP3 \u683c\u5f0f\u4e0b\u8f7d\u58f0\u97f3",
            incorrect_try_again: "\u4e0d\u6b63\u786e\uff0c\u8bf7\u91cd\u8bd5\u3002",
            image_alt_text: "reCAPTCHA \u9a8c\u8bc1\u56fe\u7247",
            privacy_and_terms: "\u9690\u79c1\u6743\u548c\u4f7f\u7528\u6761\u6b3e"
        }, ta = {
            en: t,
            af: {
                visual_challenge: "Kry 'n visuele verifi\u00ebring",
                audio_challenge: "Kry 'n klankverifi\u00ebring",
                refresh_btn: "Kry 'n nuwe verifi\u00ebring",
                instructions_visual: "",
                instructions_audio: "Tik wat jy hoor:",
                help_btn: "Hulp",
                play_again: "Speel geluid weer",
                cant_hear_this: "Laai die klank af as MP3",
                incorrect_try_again: "Verkeerd. Probeer weer.",
                image_alt_text: "reCAPTCHA-uitdagingprent",
                privacy_and_terms: "Privaatheid en bepalings"
            },
            am: {
                visual_challenge: "\u12e8\u12a5\u12ed\u1273 \u1270\u130b\u1323\u121a \u12a0\u130d\u129d",
                audio_challenge: "\u120c\u120b \u12a0\u12f2\u1235 \u12e8\u12f5\u121d\u133d \u1325\u12eb\u1244 \u12ed\u1245\u1228\u1265",
                refresh_btn: "\u120c\u120b \u12a0\u12f2\u1235 \u1325\u12eb\u1244 \u12ed\u1245\u1228\u1265",
                instructions_visual: "",
                instructions_audio: "\u12e8\u121d\u1275\u1230\u121b\u12cd\u1295 \u1270\u12ed\u1265\u1361-",
                help_btn: "\u12a5\u1308\u12db",
                play_again: "\u12f5\u121d\u1339\u1295 \u12a5\u1295\u12f0\u1308\u1293 \u12a0\u132b\u12cd\u1275",
                cant_hear_this: "\u12f5\u121d\u1339\u1295 \u1260MP3 \u1245\u122d\u133d \u12a0\u12cd\u122d\u12f5",
                incorrect_try_again: "\u1275\u12ad\u12ad\u120d \u12a0\u12ed\u12f0\u1208\u121d\u1362 \u12a5\u1295\u12f0\u1308\u1293 \u121e\u12ad\u122d\u1362",
                image_alt_text: "reCAPTCHA \u121d\u1235\u120d \u130d\u1320\u121d",
                privacy_and_terms: "\u130d\u120b\u12ca\u1290\u1275 \u12a5\u1293 \u12cd\u120d"
            },
            ar: ka,
            "ar-EG": ka,
            bg: {
                visual_challenge: "\u041f\u043e\u043b\u0443\u0447\u0430\u0432\u0430\u043d\u0435 \u043d\u0430 \u0432\u0438\u0437\u0443\u0430\u043b\u043d\u0430 \u043f\u0440\u043e\u0432\u0435\u0440\u043a\u0430",
                audio_challenge: "\u0417\u0430\u0440\u0435\u0436\u0434\u0430\u043d\u0435 \u043d\u0430 \u0430\u0443\u0434\u0438\u043e\u0442\u0435\u0441\u0442",
                refresh_btn: "\u0417\u0430\u0440\u0435\u0436\u0434\u0430\u043d\u0435 \u043d\u0430 \u043d\u043e\u0432 \u0442\u0435\u0441\u0442",
                instructions_visual: "\u0412\u044a\u0432\u0435\u0434\u0435\u0442\u0435 \u0442\u0435\u043a\u0441\u0442\u0430:",
                instructions_audio: "\u0412\u044a\u0432\u0435\u0434\u0435\u0442\u0435 \u0447\u0443\u0442\u043e\u0442\u043e:",
                help_btn: "\u041f\u043e\u043c\u043e\u0449",
                play_again: "\u041f\u043e\u0432\u0442\u043e\u0440\u043d\u043e \u043f\u0443\u0441\u043a\u0430\u043d\u0435 \u043d\u0430 \u0437\u0432\u0443\u043a\u0430",
                cant_hear_this: "\u0418\u0437\u0442\u0435\u0433\u043b\u044f\u043d\u0435 \u043d\u0430 \u0437\u0432\u0443\u043a\u0430 \u0432\u044a\u0432 \u0444\u043e\u0440\u043c\u0430\u0442 MP3",
                incorrect_try_again: "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u043d\u043e. \u041e\u043f\u0438\u0442\u0430\u0439\u0442\u0435 \u043e\u0442\u043d\u043e\u0432\u043e.",
                image_alt_text: "\u0418\u0437\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u0438\u0435 \u043d\u0430 \u043f\u0440\u043e\u0432\u0435\u0440\u043a\u0430\u0442\u0430 \u0441 reCAPTCHA",
                privacy_and_terms: "\u041f\u043e\u0432\u0435\u0440\u0438\u0442\u0435\u043b\u043d\u043e\u0441\u0442 \u0438 \u041e\u0431\u0449\u0438 \u0443\u0441\u043b\u043e\u0432\u0438\u044f"
            },
            bn: {
                visual_challenge: "\u098f\u0995\u099f\u09bf \u09a6\u09c3\u09b6\u09cd\u09af\u09ae\u09be\u09a8 \u09aa\u09cd\u09b0\u09a4\u09bf\u09a6\u09cd\u09ac\u09a8\u09cd\u09a6\u09cd\u09ac\u09bf\u09a4\u09be \u09aa\u09be\u09a8",
                audio_challenge: "\u098f\u0995\u099f\u09bf \u0985\u09a1\u09bf\u0993 \u09aa\u09cd\u09b0\u09a4\u09bf\u09a6\u09cd\u09ac\u09a8\u09cd\u09a6\u09cd\u09ac\u09bf\u09a4\u09be  \u09aa\u09be\u09a8",
                refresh_btn: "\u098f\u0995\u099f\u09bf \u09a8\u09a4\u09c1\u09a8 \u09aa\u09cd\u09b0\u09a4\u09bf\u09a6\u09cd\u09ac\u09a8\u09cd\u09a6\u09cd\u09ac\u09bf\u09a4\u09be  \u09aa\u09be\u09a8",
                instructions_visual: "",
                instructions_audio: "\u0986\u09aa\u09a8\u09bf \u09af\u09be \u09b6\u09c1\u09a8\u099b\u09c7\u09a8 \u09a4\u09be \u09b2\u09bf\u0996\u09c1\u09a8:",
                help_btn: "\u09b8\u09b9\u09be\u09df\u09a4\u09be",
                play_again: "\u0986\u09ac\u09be\u09b0 \u09b8\u09be\u0989\u09a8\u09cd\u09a1 \u09aa\u09cd\u09b2\u09c7 \u0995\u09b0\u09c1\u09a8",
                cant_hear_this: "MP3 \u09b0\u09c2\u09aa\u09c7 \u09b6\u09ac\u09cd\u09a6 \u09a1\u09be\u0989\u09a8\u09b2\u09cb\u09a1 \u0995\u09b0\u09c1\u09a8",
                incorrect_try_again: "\u09ac\u09c7\u09a0\u09bf\u0995\u09f7 \u0986\u09ac\u09be\u09b0 \u099a\u09c7\u09b7\u09cd\u099f\u09be \u0995\u09b0\u09c1\u09a8\u09f7",
                image_alt_text: "reCAPTCHA \u099a\u09cd\u09af\u09be\u09b2\u09c7\u099e\u09cd\u099c \u099a\u09bf\u09a4\u09cd\u09b0",
                privacy_and_terms: "\u0997\u09cb\u09aa\u09a8\u09c0\u09af\u09bc\u09a4\u09be \u0993 \u09b6\u09b0\u09cd\u09a4\u09be\u09ac\u09b2\u09c0"
            },
            ca: {
                visual_challenge: "Obt\u00e9n un repte visual",
                audio_challenge: "Obteniu una pista sonora",
                refresh_btn: "Obteniu una pista nova",
                instructions_visual: "Escriviu el text:",
                instructions_audio: "Escriviu el que escolteu:",
                help_btn: "Ajuda",
                play_again: "Torna a reproduir el so",
                cant_hear_this: "Baixa el so com a MP3",
                incorrect_try_again: "No \u00e9s correcte. Torna-ho a provar.",
                image_alt_text: "Imatge del repte de reCAPTCHA",
                privacy_and_terms: "Privadesa i condicions"
            },
            cs: {
                visual_challenge: "Zobrazit vizu\u00e1ln\u00ed podobu v\u00fdrazu",
                audio_challenge: "P\u0159ehr\u00e1t zvukovou podobu v\u00fdrazu",
                refresh_btn: "Zobrazit nov\u00fd v\u00fdraz",
                instructions_visual: "Zadejte text:",
                instructions_audio: "Napi\u0161te, co jste sly\u0161eli:",
                help_btn: "N\u00e1pov\u011bda",
                play_again: "Znovu p\u0159ehr\u00e1t zvuk",
                cant_hear_this: "St\u00e1hnout zvuk ve form\u00e1tu MP3",
                incorrect_try_again: "\u0160patn\u011b. Zkuste to znovu.",
                image_alt_text: "Obr\u00e1zek reCAPTCHA",
                privacy_and_terms: "Ochrana soukrom\u00ed a smluvn\u00ed podm\u00ednky"
            },
            da: {
                visual_challenge: "Hent en visuel udfordring",
                audio_challenge: "Hent en lydudfordring",
                refresh_btn: "Hent en ny udfordring",
                instructions_visual: "Indtast teksten:",
                instructions_audio: "Indtast det, du h\u00f8rer:",
                help_btn: "Hj\u00e6lp",
                play_again: "Afspil lyden igen",
                cant_hear_this: "Download lyd som MP3",
                incorrect_try_again: "Forkert. Pr\u00f8v igen.",
                image_alt_text: "reCAPTCHA-udfordringsbillede",
                privacy_and_terms: "Privatliv og vilk\u00e5r"
            },
            de: {
                visual_challenge: "Captcha abrufen",
                audio_challenge: "Audio-Captcha abrufen",
                refresh_btn: "Neues Captcha abrufen",
                instructions_visual: "Geben Sie den angezeigten Text ein:",
                instructions_audio: "Geben Sie das Geh\u00f6rte ein:",
                help_btn: "Hilfe",
                play_again: "Wort erneut abspielen",
                cant_hear_this: "Wort als MP3 herunterladen",
                incorrect_try_again: "Falsch. Bitte versuchen Sie es erneut.",
                image_alt_text: "reCAPTCHA-Bild",
                privacy_and_terms: "Datenschutzerkl\u00e4rung & Nutzungsbedingungen"
            },
            el: {
                visual_challenge: "\u039f\u03c0\u03c4\u03b9\u03ba\u03ae \u03c0\u03c1\u03cc\u03ba\u03bb\u03b7\u03c3\u03b7",
                audio_challenge: "\u0397\u03c7\u03b7\u03c4\u03b9\u03ba\u03ae \u03c0\u03c1\u03cc\u03ba\u03bb\u03b7\u03c3\u03b7",
                refresh_btn: "\u039d\u03ad\u03b1 \u03c0\u03c1\u03cc\u03ba\u03bb\u03b7\u03c3\u03b7",
                instructions_visual: "\u03a0\u03bb\u03b7\u03ba\u03c4\u03c1\u03bf\u03bb\u03bf\u03b3\u03ae\u03c3\u03c4\u03b5 \u03c4\u03bf \u03ba\u03b5\u03af\u03bc\u03b5\u03bd\u03bf:",
                instructions_audio: "\u03a0\u03bb\u03b7\u03ba\u03c4\u03c1\u03bf\u03bb\u03bf\u03b3\u03ae\u03c3\u03c4\u03b5 \u03cc\u03c4\u03b9 \u03b1\u03ba\u03bf\u03cd\u03c4\u03b5:",
                help_btn: "\u0392\u03bf\u03ae\u03b8\u03b5\u03b9\u03b1",
                play_again: "\u0391\u03bd\u03b1\u03c0\u03b1\u03c1\u03b1\u03b3\u03c9\u03b3\u03ae \u03ae\u03c7\u03bf\u03c5 \u03be\u03b1\u03bd\u03ac",
                cant_hear_this: "\u039b\u03ae\u03c8\u03b7 \u03ae\u03c7\u03bf\u03c5 \u03c9\u03c2 \u039c\u03a13",
                incorrect_try_again: "\u039b\u03ac\u03b8\u03bf\u03c2. \u0394\u03bf\u03ba\u03b9\u03bc\u03ac\u03c3\u03c4\u03b5 \u03be\u03b1\u03bd\u03ac.",
                image_alt_text: "\u0395\u03b9\u03ba\u03cc\u03bd\u03b1 \u03c0\u03c1\u03cc\u03ba\u03bb\u03b7\u03c3\u03b7\u03c2 reCAPTCHA",
                privacy_and_terms: "\u0391\u03c0\u03cc\u03c1\u03c1\u03b7\u03c4\u03bf \u03ba\u03b1\u03b9 \u03cc\u03c1\u03bf\u03b9"
            },
            "en-GB": t,
            "en-US": t,
            es: la,
            "es-419": {
                visual_challenge: "Enfrentar un desaf\u00edo visual",
                audio_challenge: "Enfrentar un desaf\u00edo de audio",
                refresh_btn: "Enfrentar un nuevo desaf\u00edo",
                instructions_visual: "Escriba el texto:",
                instructions_audio: "Escribe lo que escuchas:",
                help_btn: "Ayuda",
                play_again: "Reproducir sonido de nuevo",
                cant_hear_this: "Descargar sonido en formato MP3",
                incorrect_try_again: "Incorrecto. Vuelve a intentarlo.",
                image_alt_text: "Imagen del desaf\u00edo de la reCAPTCHA",
                privacy_and_terms: "Privacidad y condiciones"
            },
            "es-ES": la,
            et: {
                visual_challenge: "Kuva kuvap\u00f5hine robotil\u00f5ks",
                audio_challenge: "Kuva helip\u00f5hine robotil\u00f5ks",
                refresh_btn: "Kuva uus robotil\u00f5ks",
                instructions_visual: "Tippige tekst:",
                instructions_audio: "Tippige, mida kuulete.",
                help_btn: "Abi",
                play_again: "Esita heli uuesti",
                cant_hear_this: "Laadi heli alla MP3-vormingus",
                incorrect_try_again: "Vale. Proovige uuesti.",
                image_alt_text: "reCAPTCHA robotil\u00f5ksu kujutis",
                privacy_and_terms: "Privaatsus ja tingimused"
            },
            eu: {
                visual_challenge: "Eskuratu ikusizko erronka",
                audio_challenge: "Eskuratu audio-erronka",
                refresh_btn: "Eskuratu erronka berria",
                instructions_visual: "",
                instructions_audio: "Idatzi entzuten duzuna:",
                help_btn: "Laguntza",
                play_again: "Erreproduzitu soinua berriro",
                cant_hear_this: "Deskargatu soinua MP3 gisa",
                incorrect_try_again: "Ez da zuzena. Saiatu berriro.",
                image_alt_text: "reCAPTCHA erronkaren irudia",
                privacy_and_terms: "Pribatutasuna eta baldintzak"
            },
            fa: {
                visual_challenge: "\u062f\u0631\u06cc\u0627\u0641\u062a \u06cc\u06a9 \u0645\u0639\u0645\u0627\u06cc \u062f\u06cc\u062f\u0627\u0631\u06cc",
                audio_challenge: "\u062f\u0631\u06cc\u0627\u0641\u062a \u06cc\u06a9 \u0645\u0639\u0645\u0627\u06cc \u0635\u0648\u062a\u06cc",
                refresh_btn: "\u062f\u0631\u06cc\u0627\u0641\u062a \u06cc\u06a9 \u0645\u0639\u0645\u0627\u06cc \u062c\u062f\u06cc\u062f",
                instructions_visual: "",
                instructions_audio: "\u0622\u0646\u0686\u0647 \u0631\u0627 \u06a9\u0647 \u0645\u06cc\u200c\u0634\u0646\u0648\u06cc\u062f \u062a\u0627\u06cc\u067e \u06a9\u0646\u06cc\u062f:",
                help_btn: "\u0631\u0627\u0647\u0646\u0645\u0627\u06cc\u06cc",
                play_again: "\u067e\u062e\u0634 \u0645\u062c\u062f\u062f \u0635\u062f\u0627",
                cant_hear_this: "\u062f\u0627\u0646\u0644\u0648\u062f \u0635\u062f\u0627 \u0628\u0647 \u0635\u0648\u0631\u062a MP3",
                incorrect_try_again: "\u0646\u0627\u062f\u0631\u0633\u062a. \u062f\u0648\u0628\u0627\u0631\u0647 \u0627\u0645\u062a\u062d\u0627\u0646 \u06a9\u0646\u06cc\u062f.",
                image_alt_text: "\u062a\u0635\u0648\u06cc\u0631 \u0686\u0627\u0644\u0634\u06cc reCAPTCHA",
                privacy_and_terms: "\u062d\u0631\u06cc\u0645 \u062e\u0635\u0648\u0635\u06cc \u0648 \u0634\u0631\u0627\u06cc\u0637"
            },
            fi: {
                visual_challenge: "Kuvavahvistus",
                audio_challenge: "\u00c4\u00e4nivahvistus",
                refresh_btn: "Uusi kuva",
                instructions_visual: "Kirjoita teksti:",
                instructions_audio: "Kirjoita kuulemasi:",
                help_btn: "Ohje",
                play_again: "Toista \u00e4\u00e4ni uudelleen",
                cant_hear_this: "Lataa \u00e4\u00e4ni MP3-tiedostona",
                incorrect_try_again: "V\u00e4\u00e4rin. Yrit\u00e4 uudelleen.",
                image_alt_text: "reCAPTCHA-kuva",
                privacy_and_terms: "Tietosuoja ja k\u00e4ytt\u00f6ehdot"
            },
            fil: ma,
            fr: na,
            "fr-CA": {
                visual_challenge: "Obtenir un test visuel",
                audio_challenge: "Obtenir un test audio",
                refresh_btn: "Obtenir un nouveau test",
                instructions_visual: "Saisissez le texte\u00a0:",
                instructions_audio: "Tapez ce que vous entendez\u00a0:",
                help_btn: "Aide",
                play_again: "Jouer le son de nouveau",
                cant_hear_this: "T\u00e9l\u00e9charger le son en format MP3",
                incorrect_try_again: "Erreur, essayez \u00e0 nouveau",
                image_alt_text: "Image reCAPTCHA",
                privacy_and_terms: "Confidentialit\u00e9 et conditions d'utilisation"
            },
            "fr-FR": na,
            gl: {
                visual_challenge: "Obter unha proba visual",
                audio_challenge: "Obter unha proba de audio",
                refresh_btn: "Obter unha proba nova",
                instructions_visual: "",
                instructions_audio: "Escribe o que escoitas:",
                help_btn: "Axuda",
                play_again: "Reproducir o son de novo",
                cant_hear_this: "Descargar son como MP3",
                incorrect_try_again: "Incorrecto. T\u00e9ntao de novo.",
                image_alt_text: "Imaxe de proba de reCAPTCHA",
                privacy_and_terms: "Privacidade e condici\u00f3ns"
            },
            gu: {
                visual_challenge: "\u0a8f\u0a95 \u0aa6\u0ac3\u0ab6\u0acd\u0aaf\u0abe\u0aa4\u0acd\u0aae\u0a95 \u0aaa\u0aa1\u0a95\u0abe\u0ab0 \u0aae\u0ac7\u0ab3\u0ab5\u0acb",
                audio_challenge: "\u0a8f\u0a95 \u0a91\u0aa1\u0abf\u0a93 \u0aaa\u0aa1\u0a95\u0abe\u0ab0 \u0aae\u0ac7\u0ab3\u0ab5\u0acb",
                refresh_btn: "\u0a8f\u0a95 \u0aa8\u0ab5\u0acb \u0aaa\u0aa1\u0a95\u0abe\u0ab0 \u0aae\u0ac7\u0ab3\u0ab5\u0acb",
                instructions_visual: "",
                instructions_audio: "\u0aa4\u0aae\u0ac7 \u0a9c\u0ac7 \u0ab8\u0abe\u0a82\u0aad\u0ab3\u0acb \u0a9b\u0acb \u0aa4\u0ac7 \u0ab2\u0a96\u0acb:",
                help_btn: "\u0ab8\u0ab9\u0abe\u0aaf",
                play_again: "\u0aa7\u0acd\u0ab5\u0aa8\u0abf \u0aab\u0ab0\u0ac0\u0aa5\u0ac0 \u0a9a\u0ab2\u0abe\u0ab5\u0acb",
                cant_hear_this: "MP3 \u0aa4\u0ab0\u0ac0\u0a95\u0ac7 \u0aa7\u0acd\u0ab5\u0aa8\u0abf\u0aa8\u0ac7 \u0aa1\u0abe\u0a89\u0aa8\u0ab2\u0acb\u0aa1 \u0a95\u0ab0\u0acb",
                incorrect_try_again: "\u0a96\u0acb\u0a9f\u0ac1\u0a82. \u0aab\u0ab0\u0ac0 \u0aaa\u0acd\u0ab0\u0aaf\u0abe\u0ab8 \u0a95\u0ab0\u0acb.",
                image_alt_text: "reCAPTCHA \u0aaa\u0aa1\u0a95\u0abe\u0ab0 \u0a9b\u0aac\u0ac0",
                privacy_and_terms: "\u0a97\u0acb\u0aaa\u0aa8\u0ac0\u0aaf\u0aa4\u0abe \u0a85\u0aa8\u0ac7 \u0ab6\u0ab0\u0aa4\u0acb"
            },
            hi: {
                visual_challenge: "\u0915\u094b\u0908 \u0935\u093f\u091c\u0941\u0905\u0932 \u091a\u0941\u0928\u094c\u0924\u0940 \u0932\u0947\u0902",
                audio_challenge: "\u0915\u094b\u0908 \u0911\u0921\u093f\u092f\u094b \u091a\u0941\u0928\u094c\u0924\u0940 \u0932\u0947\u0902",
                refresh_btn: "\u0915\u094b\u0908 \u0928\u0908 \u091a\u0941\u0928\u094c\u0924\u0940 \u0932\u0947\u0902",
                instructions_visual: "\u091f\u0947\u0915\u094d\u0938\u094d\u091f \u091f\u093e\u0907\u092a \u0915\u0930\u0947\u0902:",
                instructions_audio: "\u091c\u094b \u0906\u092a \u0938\u0941\u0928 \u0930\u0939\u0947 \u0939\u0948\u0902 \u0909\u0938\u0947 \u0932\u093f\u0916\u0947\u0902:",
                help_btn: "\u0938\u0939\u093e\u092f\u0924\u093e",
                play_again: "\u0927\u094d\u200d\u0935\u0928\u093f \u092a\u0941\u0928: \u091a\u0932\u093e\u090f\u0902",
                cant_hear_this: "\u0927\u094d\u200d\u0935\u0928\u093f \u0915\u094b MP3 \u0915\u0947 \u0930\u0942\u092a \u092e\u0947\u0902 \u0921\u093e\u0909\u0928\u0932\u094b\u0921 \u0915\u0930\u0947\u0902",
                incorrect_try_again: "\u0917\u0932\u0924. \u092a\u0941\u0928: \u092a\u094d\u0930\u092f\u093e\u0938 \u0915\u0930\u0947\u0902.",
                image_alt_text: "reCAPTCHA \u091a\u0941\u0928\u094c\u0924\u0940 \u091a\u093f\u0924\u094d\u0930",
                privacy_and_terms: "\u0917\u094b\u092a\u0928\u0940\u092f\u0924\u093e \u0914\u0930 \u0936\u0930\u094d\u0924\u0947\u0902"
            },
            hr: {
                visual_challenge: "Dohvati vizualni upit",
                audio_challenge: "Dohvati zvu\u010dni upit",
                refresh_btn: "Dohvati novi upit",
                instructions_visual: "Unesite tekst:",
                instructions_audio: "Upi\u0161ite \u0161to \u010dujete:",
                help_btn: "Pomo\u0107",
                play_again: "Ponovi zvuk",
                cant_hear_this: "Preuzmi zvuk u MP3 formatu",
                incorrect_try_again: "Nije to\u010dno. Poku\u0161ajte ponovno.",
                image_alt_text: "Slikovni izazov reCAPTCHA",
                privacy_and_terms: "Privatnost i odredbe"
            },
            hu: {
                visual_challenge: "Vizu\u00e1lis kih\u00edv\u00e1s k\u00e9r\u00e9se",
                audio_challenge: "Hangkih\u00edv\u00e1s k\u00e9r\u00e9se",
                refresh_btn: "\u00daj kih\u00edv\u00e1s k\u00e9r\u00e9se",
                instructions_visual: "\u00cdrja be a sz\u00f6veget:",
                instructions_audio: "\u00cdrja le, amit hall:",
                help_btn: "S\u00fag\u00f3",
                play_again: "Hang ism\u00e9telt lej\u00e1tsz\u00e1sa",
                cant_hear_this: "Hang let\u00f6lt\u00e9se MP3 form\u00e1tumban",
                incorrect_try_again: "Hib\u00e1s. Pr\u00f3b\u00e1lkozzon \u00fajra.",
                image_alt_text: "reCAPTCHA ellen\u0151rz\u0151 k\u00e9p",
                privacy_and_terms: "Adatv\u00e9delem \u00e9s Szerz\u0151d\u00e9si Felt\u00e9telek"
            },
            hy: {
                visual_challenge: "\u054d\u057f\u0561\u0576\u0561\u056c \u057f\u0565\u057d\u0578\u0572\u0561\u056f\u0561\u0576 \u056d\u0576\u0564\u056b\u0580",
                audio_challenge: "\u054d\u057f\u0561\u0576\u0561\u056c \u0571\u0561\u0575\u0576\u0561\u0575\u056b\u0576 \u056d\u0576\u0564\u056b\u0580",
                refresh_btn: "\u054d\u057f\u0561\u0576\u0561\u056c \u0576\u0578\u0580 \u056d\u0576\u0564\u056b\u0580",
                instructions_visual: "\u0544\u0578\u0582\u057f\u0584\u0561\u0563\u0580\u0565\u0584 \u057f\u0565\u0584\u057d\u057f\u0568\u055d",
                instructions_audio: "\u0544\u0578\u0582\u057f\u0584\u0561\u0563\u0580\u0565\u0584 \u0561\u0575\u0576, \u056b\u0576\u0579 \u056c\u057d\u0578\u0582\u0574 \u0565\u0584\u055d",
                help_btn: "\u0555\u0563\u0576\u0578\u0582\u0569\u0575\u0578\u0582\u0576",
                play_again: "\u0546\u057e\u0561\u0563\u0561\u0580\u056f\u0565\u056c \u0571\u0561\u0575\u0576\u0568 \u056f\u0580\u056f\u056b\u0576",
                cant_hear_this: "\u0532\u0565\u057c\u0576\u0565\u056c \u0571\u0561\u0575\u0576\u0568 \u0578\u0580\u057a\u0565\u057d MP3",
                incorrect_try_again: "\u054d\u056d\u0561\u056c \u0567: \u0553\u0578\u0580\u0571\u0565\u0584 \u056f\u0580\u056f\u056b\u0576:",
                image_alt_text: "reCAPTCHA \u057a\u0561\u057f\u056f\u0565\u0580\u0578\u057e \u056d\u0576\u0564\u056b\u0580",
                privacy_and_terms: "\u0533\u0561\u0572\u057f\u0576\u056b\u0578\u0582\u0569\u0575\u0561\u0576 & \u057a\u0561\u0575\u0574\u0561\u0576\u0576\u0565\u0580"
            },
            id: oa,
            is: {
                visual_challenge: "F\u00e1 a\u00f0gangspr\u00f3f sem mynd",
                audio_challenge: "F\u00e1 a\u00f0gangspr\u00f3f sem hlj\u00f3\u00f0skr\u00e1",
                refresh_btn: "F\u00e1 n\u00fdtt a\u00f0gangspr\u00f3f",
                instructions_visual: "",
                instructions_audio: "Sl\u00e1\u00f0u inn \u00fea\u00f0 sem \u00fe\u00fa heyrir:",
                help_btn: "Hj\u00e1lp",
                play_again: "Spila hlj\u00f3\u00f0 aftur",
                cant_hear_this: "S\u00e6kja hlj\u00f3\u00f0 sem MP3",
                incorrect_try_again: "Rangt. Reyndu aftur.",
                image_alt_text: "mynd reCAPTCHA a\u00f0gangspr\u00f3fs",
                privacy_and_terms: "Pers\u00f3nuvernd og skilm\u00e1lar"
            },
            it: {
                visual_challenge: "Verifica visiva",
                audio_challenge: "Verifica audio",
                refresh_btn: "Nuova verifica",
                instructions_visual: "Digita il testo:",
                instructions_audio: "Digita ci\u00f2 che senti:",
                help_btn: "Guida",
                play_again: "Riproduci di nuovo audio",
                cant_hear_this: "Scarica audio in MP3",
                incorrect_try_again: "Sbagliato. Riprova.",
                image_alt_text: "Immagine di verifica reCAPTCHA",
                privacy_and_terms: "Privacy e Termini"
            },
            iw: pa,
            ja: {
                visual_challenge: "\u753b\u50cf\u3067\u78ba\u8a8d\u3057\u307e\u3059",
                audio_challenge: "\u97f3\u58f0\u3067\u78ba\u8a8d\u3057\u307e\u3059",
                refresh_btn: "\u5225\u306e\u5358\u8a9e\u3067\u3084\u308a\u76f4\u3057\u307e\u3059",
                instructions_visual: "\u30c6\u30ad\u30b9\u30c8\u3092\u5165\u529b:",
                instructions_audio: "\u805e\u3053\u3048\u305f\u5358\u8a9e\u3092\u5165\u529b\u3057\u307e\u3059:",
                help_btn: "\u30d8\u30eb\u30d7",
                play_again: "\u3082\u3046\u4e00\u5ea6\u805e\u304f",
                cant_hear_this: "MP3 \u3067\u97f3\u58f0\u3092\u30c0\u30a6\u30f3\u30ed\u30fc\u30c9",
                incorrect_try_again: "\u6b63\u3057\u304f\u3042\u308a\u307e\u305b\u3093\u3002\u3082\u3046\u4e00\u5ea6\u3084\u308a\u76f4\u3057\u3066\u304f\u3060\u3055\u3044\u3002",
                image_alt_text: "reCAPTCHA \u78ba\u8a8d\u7528\u753b\u50cf",
                privacy_and_terms: "\u30d7\u30e9\u30a4\u30d0\u30b7\u30fc\u3068\u5229\u7528\u898f\u7d04"
            },
            kn: {
                visual_challenge: "\u0ca6\u0cc3\u0cb6\u0ccd\u0caf \u0cb8\u0cb5\u0cbe\u0cb2\u0cca\u0c82\u0ca6\u0ca8\u0ccd\u0ca8\u0cc1 \u0cb8\u0ccd\u0cb5\u0cc0\u0c95\u0cb0\u0cbf\u0cb8\u0cbf",
                audio_challenge: "\u0c86\u0ca1\u0cbf\u0caf\u0ccb \u0cb8\u0cb5\u0cbe\u0cb2\u0cca\u0c82\u0ca6\u0ca8\u0ccd\u0ca8\u0cc1 \u0cb8\u0ccd\u0cb5\u0cc0\u0c95\u0cb0\u0cbf\u0cb8\u0cbf",
                refresh_btn: "\u0cb9\u0cca\u0cb8 \u0cb8\u0cb5\u0cbe\u0cb2\u0cca\u0c82\u0ca6\u0ca8\u0ccd\u0ca8\u0cc1 \u0caa\u0ca1\u0cc6\u0caf\u0cbf\u0cb0\u0cbf",
                instructions_visual: "",
                instructions_audio: "\u0ca8\u0cbf\u0cae\u0c97\u0cc6 \u0c95\u0cc7\u0cb3\u0cbf\u0cb8\u0cc1\u0cb5\u0cc1\u0ca6\u0ca8\u0ccd\u0ca8\u0cc1 \u0c9f\u0cc8\u0caa\u0ccd\u200c \u0cae\u0cbe\u0ca1\u0cbf:",
                help_btn: "\u0cb8\u0cb9\u0cbe\u0caf",
                play_again: "\u0ca7\u0ccd\u0cb5\u0ca8\u0cbf\u0caf\u0ca8\u0ccd\u0ca8\u0cc1 \u0cae\u0ca4\u0ccd\u0ca4\u0cc6 \u0caa\u0ccd\u0cb2\u0cc7 \u0cae\u0cbe\u0ca1\u0cbf",
                cant_hear_this: "\u0ca7\u0ccd\u0cb5\u0ca8\u0cbf\u0caf\u0ca8\u0ccd\u0ca8\u0cc1 MP3 \u0cb0\u0cc2\u0caa\u0ca6\u0cb2\u0ccd\u0cb2\u0cbf \u0ca1\u0ccc\u0ca8\u0ccd\u200c\u0cb2\u0ccb\u0ca1\u0ccd \u0cae\u0cbe\u0ca1\u0cbf",
                incorrect_try_again: "\u0ca4\u0caa\u0ccd\u0caa\u0cbe\u0c97\u0cbf\u0ca6\u0cc6. \u0cae\u0ca4\u0ccd\u0ca4\u0cca\u0cae\u0ccd\u0cae\u0cc6 \u0caa\u0ccd\u0cb0\u0caf\u0ca4\u0ccd\u0ca8\u0cbf\u0cb8\u0cbf.",
                image_alt_text: "reCAPTCHA \u0cb8\u0cb5\u0cbe\u0cb2\u0cc1 \u0c9a\u0cbf\u0ca4\u0ccd\u0cb0",
                privacy_and_terms: "\u0c97\u0ccc\u0caa\u0ccd\u0caf\u0ca4\u0cc6 \u0cae\u0ca4\u0ccd\u0ca4\u0cc1 \u0ca8\u0cbf\u0caf\u0cae\u0c97\u0cb3\u0cc1"
            },
            ko: {
                visual_challenge: "\uadf8\ub9bc\uc73c\ub85c \ubcf4\uc548\ubb38\uc790 \ubc1b\uae30",
                audio_challenge: "\uc74c\uc131\uc73c\ub85c \ubcf4\uc548\ubb38\uc790 \ubc1b\uae30",
                refresh_btn: "\ubcf4\uc548\ubb38\uc790 \uc0c8\ub85c \ubc1b\uae30",
                instructions_visual: "\ud14d\uc2a4\ud2b8 \uc785\ub825:",
                instructions_audio: "\uc74c\uc131 \ubcf4\uc548\ubb38\uc790 \uc785\ub825:",
                help_btn: "\ub3c4\uc6c0\ub9d0",
                play_again: "\uc74c\uc131 \ub2e4\uc2dc \ub4e3\uae30",
                cant_hear_this: "\uc74c\uc131\uc744 MP3\ub85c \ub2e4\uc6b4\ub85c\ub4dc",
                incorrect_try_again: "\ud2c0\ub838\uc2b5\ub2c8\ub2e4. \ub2e4\uc2dc \uc2dc\ub3c4\ud574 \uc8fc\uc138\uc694.",
                image_alt_text: "reCAPTCHA \ubcf4\uc548\ubb38\uc790 \uc774\ubbf8\uc9c0",
                privacy_and_terms: "\uac1c\uc778\uc815\ubcf4 \ubcf4\ud638 \ubc0f \uc57d\uad00"
            },
            ln: na,
            lt: {
                visual_challenge: "Gauti vaizdin\u012f atpa\u017einimo test\u0105",
                audio_challenge: "Gauti garso atpa\u017einimo test\u0105",
                refresh_btn: "Gauti nauj\u0105 atpa\u017einimo test\u0105",
                instructions_visual: "\u012eveskite tekst\u0105:",
                instructions_audio: "\u012eveskite tai, k\u0105 girdite:",
                help_btn: "Pagalba",
                play_again: "Dar kart\u0105 paleisti gars\u0105",
                cant_hear_this: "Atsisi\u0173sti gars\u0105 kaip MP3",
                incorrect_try_again: "Neteisingai. Bandykite dar kart\u0105.",
                image_alt_text: "Testo \u201ereCAPTCHA\u201c vaizdas",
                privacy_and_terms: "Privatumas ir s\u0105lygos"
            },
            lv: {
                visual_challenge: "Sa\u0146emt vizu\u0101lu izaicin\u0101jumu",
                audio_challenge: "Sa\u0146emt audio izaicin\u0101jumu",
                refresh_btn: "Sa\u0146emt jaunu izaicin\u0101jumu",
                instructions_visual: "Ievadiet tekstu:",
                instructions_audio: "Ierakstiet dzirdamo:",
                help_btn: "Pal\u012bdz\u012bba",
                play_again: "V\u0113lreiz atska\u0146ot ska\u0146u",
                cant_hear_this: "Lejupiel\u0101d\u0113t ska\u0146u MP3\u00a0form\u0101t\u0101",
                incorrect_try_again: "Nepareizi. M\u0113\u0123iniet v\u0113lreiz.",
                image_alt_text: "reCAPTCHA izaicin\u0101juma att\u0113ls",
                privacy_and_terms: "Konfidencialit\u0101te un noteikumi"
            },
            ml: {
                visual_challenge: "\u0d12\u0d30\u0d41 \u0d26\u0d43\u0d36\u0d4d\u0d2f \u0d1a\u0d32\u0d1e\u0d4d\u0d1a\u0d4d \u0d28\u0d47\u0d1f\u0d41\u0d15",
                audio_challenge: "\u0d12\u0d30\u0d41 \u0d13\u0d21\u0d3f\u0d2f\u0d4b \u0d1a\u0d32\u0d1e\u0d4d\u0d1a\u0d4d \u0d28\u0d47\u0d1f\u0d41\u0d15",
                refresh_btn: "\u0d12\u0d30\u0d41 \u0d2a\u0d41\u0d24\u0d3f\u0d2f \u0d1a\u0d32\u0d1e\u0d4d\u0d1a\u0d4d \u0d28\u0d47\u0d1f\u0d41\u0d15",
                instructions_visual: "",
                instructions_audio: "\u0d15\u0d47\u0d7e\u0d15\u0d4d\u0d15\u0d41\u0d28\u0d4d\u0d28\u0d24\u0d4d \u0d1f\u0d48\u0d2a\u0d4d\u0d2a\u0d4d \u0d1a\u0d46\u0d2f\u0d4d\u0d2f\u0d42:",
                help_btn: "\u0d38\u0d39\u0d3e\u0d2f\u0d02",
                play_again: "\u0d36\u0d2c\u0d4d\u200c\u0d26\u0d02 \u0d35\u0d40\u0d23\u0d4d\u0d1f\u0d41\u0d02 \u0d2a\u0d4d\u0d32\u0d47 \u0d1a\u0d46\u0d2f\u0d4d\u0d2f\u0d41\u0d15",
                cant_hear_this: "\u0d36\u0d2c\u0d4d\u200c\u0d26\u0d02 MP3 \u0d06\u0d2f\u0d3f \u0d21\u0d57\u0d7a\u0d32\u0d4b\u0d21\u0d4d \u0d1a\u0d46\u0d2f\u0d4d\u0d2f\u0d41\u0d15",
                incorrect_try_again: "\u0d24\u0d46\u0d31\u0d4d\u0d31\u0d3e\u0d23\u0d4d. \u0d35\u0d40\u0d23\u0d4d\u0d1f\u0d41\u0d02 \u0d36\u0d4d\u0d30\u0d2e\u0d3f\u0d15\u0d4d\u0d15\u0d41\u0d15.",
                image_alt_text: "reCAPTCHA \u0d1a\u0d32\u0d1e\u0d4d\u0d1a\u0d4d \u0d07\u0d2e\u0d47\u0d1c\u0d4d",
                privacy_and_terms: "\u0d38\u0d4d\u0d35\u0d15\u0d3e\u0d30\u0d4d\u0d2f\u0d24\u0d2f\u0d41\u0d02 \u0d28\u0d3f\u0d2c\u0d28\u0d4d\u0d27\u0d28\u0d15\u0d33\u0d41\u0d02"
            },
            mr: {
                visual_challenge: "\u0926\u0943\u0936\u094d\u200d\u092f\u092e\u093e\u0928 \u0906\u0935\u094d\u0939\u093e\u0928 \u092a\u094d\u0930\u093e\u092a\u094d\u0924 \u0915\u0930\u093e",
                audio_challenge: "\u0911\u0921\u0940\u0913 \u0906\u0935\u094d\u0939\u093e\u0928 \u092a\u094d\u0930\u093e\u092a\u094d\u0924 \u0915\u0930\u093e",
                refresh_btn: "\u090f\u0915 \u0928\u0935\u0940\u0928 \u0906\u0935\u094d\u0939\u093e\u0928 \u092a\u094d\u0930\u093e\u092a\u094d\u0924 \u0915\u0930\u093e",
                instructions_visual: "",
                instructions_audio: "\u0906\u092a\u0932\u094d\u092f\u093e\u0932\u093e \u091c\u0947 \u0910\u0915\u0942 \u092f\u0947\u0908\u0932 \u0924\u0947 \u091f\u093e\u0907\u092a \u0915\u0930\u093e:",
                help_btn: "\u092e\u0926\u0924",
                play_again: "\u0927\u094d\u200d\u0935\u0928\u0940 \u092a\u0941\u0928\u094d\u0939\u093e \u092a\u094d\u200d\u0932\u0947 \u0915\u0930\u093e",
                cant_hear_this: "MP3 \u0930\u0941\u092a\u093e\u0924 \u0927\u094d\u200d\u0935\u0928\u0940 \u0921\u093e\u0909\u0928\u0932\u094b\u0921 \u0915\u0930\u093e",
                incorrect_try_again: "\u0905\u092f\u094b\u0917\u094d\u200d\u092f. \u092a\u0941\u0928\u094d\u200d\u0939\u093e \u092a\u094d\u0930\u092f\u0924\u094d\u200d\u0928 \u0915\u0930\u093e.",
                image_alt_text: "reCAPTCHA \u0906\u0935\u094d\u200d\u0939\u093e\u0928 \u092a\u094d\u0930\u0924\u093f\u092e\u093e",
                privacy_and_terms: "\u0917\u094b\u092a\u0928\u0940\u092f\u0924\u093e \u0906\u0923\u093f \u0905\u091f\u0940"
            },
            ms: {
                visual_challenge: "Dapatkan cabaran visual",
                audio_challenge: "Dapatkan cabaran audio",
                refresh_btn: "Dapatkan cabaran baru",
                instructions_visual: "Taipkan teksnya:",
                instructions_audio: "Taip apa yang didengari:",
                help_btn: "Bantuan",
                play_again: "Mainkan bunyi sekali lagi",
                cant_hear_this: "Muat turun bunyi sebagai MP3",
                incorrect_try_again: "Tidak betul. Cuba lagi.",
                image_alt_text: "Imej cabaran reCAPTCHA",
                privacy_and_terms: "Privasi & Syarat"
            },
            nl: {
                visual_challenge: "Een visuele uitdaging proberen",
                audio_challenge: "Een audio-uitdaging proberen",
                refresh_btn: "Een nieuwe uitdaging proberen",
                instructions_visual: "Typ de tekst:",
                instructions_audio: "Typ wat u hoort:",
                help_btn: "Help",
                play_again: "Geluid opnieuw afspelen",
                cant_hear_this: "Geluid downloaden als MP3",
                incorrect_try_again: "Onjuist. Probeer het opnieuw.",
                image_alt_text: "reCAPTCHA-uitdagingsafbeelding",
                privacy_and_terms: "Privacy en voorwaarden"
            },
            no: {
                visual_challenge: "F\u00e5 en bildeutfordring",
                audio_challenge: "F\u00e5 en lydutfordring",
                refresh_btn: "F\u00e5 en ny utfordring",
                instructions_visual: "Skriv inn teksten:",
                instructions_audio: "Skriv inn det du h\u00f8rer:",
                help_btn: "Hjelp",
                play_again: "Spill av lyd p\u00e5 nytt",
                cant_hear_this: "Last ned lyd som MP3",
                incorrect_try_again: "Feil. Pr\u00f8v p\u00e5 nytt.",
                image_alt_text: "reCAPTCHA-utfordringsbilde",
                privacy_and_terms: "Personvern og vilk\u00e5r"
            },
            pl: {
                visual_challenge: "Poka\u017c podpowied\u017a wizualn\u0105",
                audio_challenge: "Odtw\u00f3rz podpowied\u017a d\u017awi\u0119kow\u0105",
                refresh_btn: "Nowa podpowied\u017a",
                instructions_visual: "Przepisz tekst:",
                instructions_audio: "Wpisz us\u0142yszane s\u0142owa:",
                help_btn: "Pomoc",
                play_again: "Odtw\u00f3rz d\u017awi\u0119k ponownie",
                cant_hear_this: "Pobierz d\u017awi\u0119k jako plik MP3",
                incorrect_try_again: "Nieprawid\u0142owo. Spr\u00f3buj ponownie.",
                image_alt_text: "Zadanie obrazkowe reCAPTCHA",
                privacy_and_terms: "Prywatno\u015b\u0107 i warunki"
            },
            pt: qa,
            "pt-BR": qa,
            "pt-PT": {
                visual_challenge: "Obter um desafio visual",
                audio_challenge: "Obter um desafio de \u00e1udio",
                refresh_btn: "Obter um novo desafio",
                instructions_visual: "Introduza o texto:",
                instructions_audio: "Escreva o que ouvir:",
                help_btn: "Ajuda",
                play_again: "Reproduzir som novamente",
                cant_hear_this: "Transferir som como MP3",
                incorrect_try_again: "Incorreto. Tente novamente.",
                image_alt_text: "Imagem de teste reCAPTCHA",
                privacy_and_terms: "Privacidade e Termos de Utiliza\u00e7\u00e3o"
            },
            ro: ra,
            ru: {
                visual_challenge: "\u0412\u0438\u0437\u0443\u0430\u043b\u044c\u043d\u0430\u044f \u043f\u0440\u043e\u0432\u0435\u0440\u043a\u0430",
                audio_challenge: "\u0417\u0432\u0443\u043a\u043e\u0432\u0430\u044f \u043f\u0440\u043e\u0432\u0435\u0440\u043a\u0430",
                refresh_btn: "\u041e\u0431\u043d\u043e\u0432\u0438\u0442\u044c",
                instructions_visual: "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0442\u0435\u043a\u0441\u0442:",
                instructions_audio: "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0442\u043e, \u0447\u0442\u043e \u0441\u043b\u044b\u0448\u0438\u0442\u0435:",
                help_btn: "\u0421\u043f\u0440\u0430\u0432\u043a\u0430",
                play_again: "\u041f\u0440\u043e\u0441\u043b\u0443\u0448\u0430\u0442\u044c \u0435\u0449\u0435 \u0440\u0430\u0437",
                cant_hear_this: "\u0417\u0430\u0433\u0440\u0443\u0437\u0438\u0442\u044c MP3-\u0444\u0430\u0439\u043b",
                incorrect_try_again: "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u043e. \u041f\u043e\u0432\u0442\u043e\u0440\u0438\u0442\u0435 \u043f\u043e\u043f\u044b\u0442\u043a\u0443.",
                image_alt_text: "\u041f\u0440\u043e\u0432\u0435\u0440\u043a\u0430 \u043f\u043e \u0441\u043b\u043e\u0432\u0443 reCAPTCHA",
                privacy_and_terms: "\u041f\u0440\u0430\u0432\u0438\u043b\u0430 \u0438 \u043f\u0440\u0438\u043d\u0446\u0438\u043f\u044b"
            },
            sk: {
                visual_challenge: "Zobrazi\u0165 vizu\u00e1lnu podobu",
                audio_challenge: "Prehra\u0165 zvukov\u00fa podobu",
                refresh_btn: "Zobrazi\u0165 nov\u00fd v\u00fdraz",
                instructions_visual: "Zadajte text:",
                instructions_audio: "Zadajte, \u010do po\u010dujete:",
                help_btn: "Pomocn\u00edk",
                play_again: "Znova prehra\u0165 zvuk",
                cant_hear_this: "Prevzia\u0165 zvuk v podobe s\u00faboru MP3",
                incorrect_try_again: "Nespr\u00e1vne. Sk\u00faste to znova.",
                image_alt_text: "Obr\u00e1zok zadania reCAPTCHA",
                privacy_and_terms: "Ochrana osobn\u00fdch \u00fadajov a Zmluvn\u00e9 podmienky"
            },
            sl: {
                visual_challenge: "Vizualni preskus",
                audio_challenge: "Zvo\u010dni preskus",
                refresh_btn: "Nov preskus",
                instructions_visual: "Vnesite besedilo:",
                instructions_audio: "Natipkajte, kaj sli\u0161ite:",
                help_btn: "Pomo\u010d",
                play_again: "Znova predvajaj zvok",
                cant_hear_this: "Prenesi zvok kot MP3",
                incorrect_try_again: "Napa\u010dno. Poskusite znova.",
                image_alt_text: "Slika izziva reCAPTCHA",
                privacy_and_terms: "Zasebnost in pogoji"
            },
            sr: {
                visual_challenge: "\u041f\u0440\u0438\u043c\u0438\u0442\u0435 \u0432\u0438\u0437\u0443\u0435\u043b\u043d\u0438 \u0443\u043f\u0438\u0442",
                audio_challenge: "\u041f\u0440\u0438\u043c\u0438\u0442\u0435 \u0430\u0443\u0434\u0438\u043e \u0443\u043f\u0438\u0442",
                refresh_btn: "\u041f\u0440\u0438\u043c\u0438\u0442\u0435 \u043d\u043e\u0432\u0438 \u0443\u043f\u0438\u0442",
                instructions_visual: "\u0423\u043d\u0435\u0441\u0438\u0442\u0435 \u0442\u0435\u043a\u0441\u0442:",
                instructions_audio: "\u041e\u0442\u043a\u0443\u0446\u0430\u0458\u0442\u0435 \u043e\u043d\u043e \u0448\u0442\u043e \u0447\u0443\u0458\u0435\u0442\u0435:",
                help_btn: "\u041f\u043e\u043c\u043e\u045b",
                play_again: "\u041f\u043e\u043d\u043e\u0432\u043e \u043f\u0443\u0441\u0442\u0438 \u0437\u0432\u0443\u043a",
                cant_hear_this: "\u041f\u0440\u0435\u0443\u0437\u043c\u0438 \u0437\u0432\u0443\u043a \u043a\u0430\u043e MP3 \u0441\u043d\u0438\u043c\u0430\u043a",
                incorrect_try_again: "\u041d\u0435\u0442\u0430\u0447\u043d\u043e. \u041f\u043e\u043a\u0443\u0448\u0430\u0458\u0442\u0435 \u043f\u043e\u043d\u043e\u0432\u043e.",
                image_alt_text: "\u0421\u043b\u0438\u043a\u0430 reCAPTCHA \u043f\u0440\u043e\u0432\u0435\u0440\u0435",
                privacy_and_terms: "\u041f\u0440\u0438\u0432\u0430\u0442\u043d\u043e\u0441\u0442 \u0438 \u0443\u0441\u043b\u043e\u0432\u0438"
            },
            sv: {
                visual_challenge: "H\u00e4mta captcha i bildformat",
                audio_challenge: "H\u00e4mta captcha i ljudformat",
                refresh_btn: "H\u00e4mta ny captcha",
                instructions_visual: "Skriv texten:",
                instructions_audio: "Skriv det du h\u00f6r:",
                help_btn: "Hj\u00e4lp",
                play_again: "Spela upp ljudet igen",
                cant_hear_this: "H\u00e4mta ljud som MP3",
                incorrect_try_again: "Fel. F\u00f6rs\u00f6k igen.",
                image_alt_text: "reCAPTCHA-bild",
                privacy_and_terms: "Sekretess och villkor"
            },
            sw: {
                visual_challenge: "Pata herufi za kusoma",
                audio_challenge: "Pata herufi za kusikiliza",
                refresh_btn: "Pata herufi mpya",
                instructions_visual: "",
                instructions_audio: "Charaza unachosikia:",
                help_btn: "Usaidizi",
                play_again: "Cheza sauti tena",
                cant_hear_this: "Pakua sauti kama MP3",
                incorrect_try_again: "Sio sahihi. Jaribu tena.",
                image_alt_text: "picha ya changamoto ya reCAPTCHA",
                privacy_and_terms: "Faragha & Masharti"
            },
            ta: {
                visual_challenge: "\u0baa\u0bbe\u0bb0\u0bcd\u0bb5\u0bc8 \u0b9a\u0bc7\u0bb2\u0b9e\u0bcd\u0b9a\u0bc8\u0baa\u0bcd \u0baa\u0bc6\u0bb1\u0bc1\u0b95",
                audio_challenge: "\u0b86\u0b9f\u0bbf\u0baf\u0bcb \u0b9a\u0bc7\u0bb2\u0b9e\u0bcd\u0b9a\u0bc8\u0baa\u0bcd \u0baa\u0bc6\u0bb1\u0bc1\u0b95",
                refresh_btn: "\u0baa\u0bc1\u0ba4\u0bbf\u0baf \u0b9a\u0bc7\u0bb2\u0b9e\u0bcd\u0b9a\u0bc8\u0baa\u0bcd \u0baa\u0bc6\u0bb1\u0bc1\u0b95",
                instructions_visual: "",
                instructions_audio: "\u0b95\u0bc7\u0b9f\u0bcd\u0baa\u0ba4\u0bc8 \u0b9f\u0bc8\u0baa\u0bcd \u0b9a\u0bc6\u0baf\u0bcd\u0b95:",
                help_btn: "\u0b89\u0ba4\u0bb5\u0bbf",
                play_again: "\u0b92\u0bb2\u0bbf\u0baf\u0bc8 \u0bae\u0bc0\u0ba3\u0bcd\u0b9f\u0bc1\u0bae\u0bcd \u0b87\u0baf\u0b95\u0bcd\u0b95\u0bc1",
                cant_hear_this: "\u0b92\u0bb2\u0bbf\u0baf\u0bc8 MP3 \u0b86\u0b95 \u0baa\u0ba4\u0bbf\u0bb5\u0bbf\u0bb1\u0b95\u0bcd\u0b95\u0bc1\u0b95",
                incorrect_try_again: "\u0ba4\u0bb5\u0bb1\u0bbe\u0ba9\u0ba4\u0bc1. \u0bae\u0bc0\u0ba3\u0bcd\u0b9f\u0bc1\u0bae\u0bcd \u0bae\u0bc1\u0baf\u0bb2\u0bb5\u0bc1\u0bae\u0bcd.",
                image_alt_text: "reCAPTCHA \u0b9a\u0bc7\u0bb2\u0b9e\u0bcd\u0b9a\u0bcd \u0baa\u0b9f\u0bae\u0bcd",
                privacy_and_terms: "\u0ba4\u0ba9\u0bbf\u0baf\u0bc1\u0bb0\u0bbf\u0bae\u0bc8 & \u0bb5\u0bbf\u0ba4\u0bbf\u0bae\u0bc1\u0bb1\u0bc8\u0b95\u0bb3\u0bcd"
            },
            te: {
                visual_challenge: "\u0c12\u0c15 \u0c26\u0c43\u0c36\u0c4d\u0c2f\u0c2e\u0c3e\u0c28 \u0c38\u0c35\u0c3e\u0c32\u0c41\u0c28\u0c41 \u0c38\u0c4d\u0c35\u0c40\u0c15\u0c30\u0c3f\u0c02\u0c1a\u0c02\u0c21\u0c3f",
                audio_challenge: "\u0c12\u0c15 \u0c06\u0c21\u0c3f\u0c2f\u0c4b \u0c38\u0c35\u0c3e\u0c32\u0c41\u0c28\u0c41 \u0c38\u0c4d\u0c35\u0c40\u0c15\u0c30\u0c3f\u0c02\u0c1a\u0c02\u0c21\u0c3f",
                refresh_btn: "\u0c15\u0c4d\u0c30\u0c4a\u0c24\u0c4d\u0c24 \u0c38\u0c35\u0c3e\u0c32\u0c41\u0c28\u0c41 \u0c38\u0c4d\u0c35\u0c40\u0c15\u0c30\u0c3f\u0c02\u0c1a\u0c02\u0c21\u0c3f",
                instructions_visual: "",
                instructions_audio: "\u0c2e\u0c40\u0c30\u0c41 \u0c35\u0c3f\u0c28\u0c4d\u0c28\u0c26\u0c3f \u0c1f\u0c48\u0c2a\u0c4d \u0c1a\u0c47\u0c2f\u0c02\u0c21\u0c3f:",
                help_btn: "\u0c38\u0c39\u0c3e\u0c2f\u0c02",
                play_again: "\u0c27\u0c4d\u0c35\u0c28\u0c3f\u0c28\u0c3f \u0c2e\u0c33\u0c4d\u0c32\u0c40 \u0c2a\u0c4d\u0c32\u0c47 \u0c1a\u0c47\u0c2f\u0c3f",
                cant_hear_this: "\u0c27\u0c4d\u0c35\u0c28\u0c3f\u0c28\u0c3f MP3 \u0c35\u0c32\u0c46 \u0c21\u0c4c\u0c28\u0c4d\u200c\u0c32\u0c4b\u0c21\u0c4d \u0c1a\u0c47\u0c2f\u0c3f",
                incorrect_try_again: "\u0c24\u0c2a\u0c4d\u0c2a\u0c41. \u0c2e\u0c33\u0c4d\u0c32\u0c40 \u0c2a\u0c4d\u0c30\u0c2f\u0c24\u0c4d\u0c28\u0c3f\u0c02\u0c1a\u0c02\u0c21\u0c3f.",
                image_alt_text: "reCAPTCHA \u0c38\u0c35\u0c3e\u0c32\u0c41 \u0c1a\u0c3f\u0c24\u0c4d\u0c30\u0c02",
                privacy_and_terms: "\u0c17\u0c4b\u0c2a\u0c4d\u0c2f\u0c24 & \u0c28\u0c3f\u0c2c\u0c02\u0c27\u0c28\u0c32\u0c41"
            },
            th: {
                visual_challenge: "\u0e23\u0e31\u0e1a\u0e04\u0e27\u0e32\u0e21\u0e17\u0e49\u0e32\u0e17\u0e32\u0e22\u0e14\u0e49\u0e32\u0e19\u0e20\u0e32\u0e1e",
                audio_challenge: "\u0e23\u0e31\u0e1a\u0e04\u0e27\u0e32\u0e21\u0e17\u0e49\u0e32\u0e17\u0e32\u0e22\u0e14\u0e49\u0e32\u0e19\u0e40\u0e2a\u0e35\u0e22\u0e07",
                refresh_btn: "\u0e23\u0e31\u0e1a\u0e04\u0e27\u0e32\u0e21\u0e17\u0e49\u0e32\u0e17\u0e32\u0e22\u0e43\u0e2b\u0e21\u0e48",
                instructions_visual: "\u0e1e\u0e34\u0e21\u0e1e\u0e4c\u0e02\u0e49\u0e2d\u0e04\u0e27\u0e32\u0e21\u0e19\u0e35\u0e49:",
                instructions_audio: "\u0e1e\u0e34\u0e21\u0e1e\u0e4c\u0e2a\u0e34\u0e48\u0e07\u0e17\u0e35\u0e48\u0e04\u0e38\u0e13\u0e44\u0e14\u0e49\u0e22\u0e34\u0e19:",
                help_btn: "\u0e04\u0e27\u0e32\u0e21\u0e0a\u0e48\u0e27\u0e22\u0e40\u0e2b\u0e25\u0e37\u0e2d",
                play_again: "\u0e40\u0e25\u0e48\u0e19\u0e40\u0e2a\u0e35\u0e22\u0e07\u0e2d\u0e35\u0e01\u0e04\u0e23\u0e31\u0e49\u0e07",
                cant_hear_this: "\u0e14\u0e32\u0e27\u0e42\u0e2b\u0e25\u0e14\u0e40\u0e2a\u0e35\u0e22\u0e07\u0e40\u0e1b\u0e47\u0e19 MP3",
                incorrect_try_again: "\u0e44\u0e21\u0e48\u0e16\u0e39\u0e01\u0e15\u0e49\u0e2d\u0e07 \u0e25\u0e2d\u0e07\u0e2d\u0e35\u0e01\u0e04\u0e23\u0e31\u0e49\u0e07",
                image_alt_text: "\u0e23\u0e2b\u0e31\u0e2a\u0e20\u0e32\u0e1e reCAPTCHA",
                privacy_and_terms: "\u0e19\u0e42\u0e22\u0e1a\u0e32\u0e22\u0e2a\u0e48\u0e27\u0e19\u0e1a\u0e38\u0e04\u0e04\u0e25\u0e41\u0e25\u0e30\u0e02\u0e49\u0e2d\u0e01\u0e33\u0e2b\u0e19\u0e14"
            },
            tr: {
                visual_challenge: "G\u00f6rsel sorgu al",
                audio_challenge: "Sesli sorgu al",
                refresh_btn: "Yeniden y\u00fckle",
                instructions_visual: "Metni yaz\u0131n:",
                instructions_audio: "Duydu\u011funuzu yaz\u0131n:",
                help_btn: "Yard\u0131m",
                play_again: "Sesi tekrar \u00e7al",
                cant_hear_this: "Sesi MP3 olarak indir",
                incorrect_try_again: "Yanl\u0131\u015f. Tekrar deneyin.",
                image_alt_text: "reCAPTCHA sorusu resmi",
                privacy_and_terms: "Gizlilik ve \u015eartlar"
            },
            uk: {
                visual_challenge: "\u041e\u0442\u0440\u0438\u043c\u0430\u0442\u0438 \u0432\u0456\u0437\u0443\u0430\u043b\u044c\u043d\u0438\u0439 \u0442\u0435\u043a\u0441\u0442",
                audio_challenge: "\u041e\u0442\u0440\u0438\u043c\u0430\u0442\u0438 \u0430\u0443\u0434\u0456\u043e\u0437\u0430\u043f\u0438\u0441",
                refresh_btn: "\u041e\u043d\u043e\u0432\u0438\u0442\u0438 \u0442\u0435\u043a\u0441\u0442",
                instructions_visual: "\u0412\u0432\u0435\u0434\u0456\u0442\u044c \u0442\u0435\u043a\u0441\u0442:",
                instructions_audio: "\u0412\u0432\u0435\u0434\u0456\u0442\u044c \u043f\u043e\u0447\u0443\u0442\u0435:",
                help_btn: "\u0414\u043e\u0432\u0456\u0434\u043a\u0430",
                play_again: "\u0412\u0456\u0434\u0442\u0432\u043e\u0440\u0438\u0442\u0438 \u0437\u0430\u043f\u0438\u0441 \u0449\u0435 \u0440\u0430\u0437",
                cant_hear_this: "\u0417\u0430\u0432\u0430\u043d\u0442\u0430\u0436\u0438\u0442\u0438 \u0437\u0430\u043f\u0438\u0441 \u044f\u043a MP3",
                incorrect_try_again: "\u041d\u0435\u043f\u0440\u0430\u0432\u0438\u043b\u044c\u043d\u043e. \u0421\u043f\u0440\u043e\u0431\u0443\u0439\u0442\u0435 \u0449\u0435 \u0440\u0430\u0437.",
                image_alt_text: "\u0417\u043e\u0431\u0440\u0430\u0436\u0435\u043d\u043d\u044f \u0437\u0430\u0432\u0434\u0430\u043d\u043d\u044f reCAPTCHA",
                privacy_and_terms: "\u041a\u043e\u043d\u0444\u0456\u0434\u0435\u043d\u0446\u0456\u0439\u043d\u0456\u0441\u0442\u044c \u0456 \u0443\u043c\u043e\u0432\u0438"
            },
            ur: {
                visual_challenge: "\u0627\u06cc\u06a9 \u0645\u0631\u0626\u06cc \u0686\u06cc\u0644\u0646\u062c \u062d\u0627\u0635\u0644 \u06a9\u0631\u06cc\u06ba",
                audio_challenge: "\u0627\u06cc\u06a9 \u0622\u0688\u06cc\u0648 \u0686\u06cc\u0644\u0646\u062c \u062d\u0627\u0635\u0644 \u06a9\u0631\u06cc\u06ba",
                refresh_btn: "\u0627\u06cc\u06a9 \u0646\u06cc\u0627 \u0686\u06cc\u0644\u0646\u062c \u062d\u0627\u0635\u0644 \u06a9\u0631\u06cc\u06ba",
                instructions_visual: "",
                instructions_audio: "\u062c\u0648 \u0633\u0646\u0627\u0626\u06cc \u062f\u06cc\u062a\u0627 \u06c1\u06d2 \u0648\u06c1 \u0679\u0627\u0626\u067e \u06a9\u0631\u06cc\u06ba:",
                help_btn: "\u0645\u062f\u062f",
                play_again: "\u0622\u0648\u0627\u0632 \u062f\u0648\u0628\u0627\u0631\u06c1 \u0686\u0644\u0627\u0626\u06cc\u06ba",
                cant_hear_this: "\u0622\u0648\u0627\u0632 \u06a9\u0648 MP3 \u06a9\u06d2 \u0628\u0637\u0648\u0631 \u0688\u0627\u0624\u0646 \u0644\u0648\u0688 \u06a9\u0631\u06cc\u06ba",
                incorrect_try_again: "\u063a\u0644\u0637\u06d4 \u062f\u0648\u0628\u0627\u0631\u06c1 \u06a9\u0648\u0634\u0634 \u06a9\u0631\u06cc\u06ba\u06d4",
                image_alt_text: "reCAPTCHA \u0686\u06cc\u0644\u0646\u062c \u0648\u0627\u0644\u06cc \u0634\u0628\u06cc\u06c1",
                privacy_and_terms: "\u0631\u0627\u0632\u062f\u0627\u0631\u06cc \u0648 \u0634\u0631\u0627\u0626\u0637"
            },
            vi: {
                visual_challenge: "Nh\u1eadn th\u1eed th\u00e1ch h\u00ecnh \u1ea3nh",
                audio_challenge: "Nh\u1eadn th\u1eed th\u00e1ch \u00e2m thanh",
                refresh_btn: "Nh\u1eadn th\u1eed th\u00e1ch m\u1edbi",
                instructions_visual: "Nh\u1eadp v\u0103n b\u1ea3n:",
                instructions_audio: "Nh\u1eadp n\u1ed9i dung b\u1ea1n nghe th\u1ea5y:",
                help_btn: "Tr\u1ee3 gi\u00fap",
                play_again: "Ph\u00e1t l\u1ea1i \u00e2m thanh",
                cant_hear_this: "T\u1ea3i \u00e2m thanh xu\u1ed1ng d\u01b0\u1edbi d\u1ea1ng MP3",
                incorrect_try_again: "Kh\u00f4ng ch\u00ednh x\u00e1c. H\u00e3y th\u1eed l\u1ea1i.",
                image_alt_text: "H\u00ecnh x\u00e1c th\u1ef1c reCAPTCHA",
                privacy_and_terms: "B\u1ea3o m\u1eadt v\u00e0 \u0111i\u1ec1u kho\u1ea3n"
            },
            "zh-CN": sa,
            "zh-HK": {
                visual_challenge: "\u56de\u7b54\u5716\u50cf\u9a57\u8b49\u554f\u984c",
                audio_challenge: "\u53d6\u5f97\u8a9e\u97f3\u9a57\u8b49\u554f\u984c",
                refresh_btn: "\u63db\u4e00\u500b\u9a57\u8b49\u554f\u984c",
                instructions_visual: "\u8f38\u5165\u6587\u5b57\uff1a",
                instructions_audio: "\u9375\u5165\u60a8\u6240\u807d\u5230\u7684\uff1a",
                help_btn: "\u8aaa\u660e",
                play_again: "\u518d\u6b21\u64ad\u653e\u8072\u97f3",
                cant_hear_this: "\u5c07\u8072\u97f3\u4e0b\u8f09\u70ba MP3",
                incorrect_try_again: "\u4e0d\u6b63\u78ba\uff0c\u518d\u8a66\u4e00\u6b21\u3002",
                image_alt_text: "reCAPTCHA \u9a57\u8b49\u6587\u5b57\u5716\u7247",
                privacy_and_terms: "\u79c1\u96b1\u6b0a\u8207\u689d\u6b3e"
            },
            "zh-TW": {
                visual_challenge: "\u53d6\u5f97\u5716\u7247\u9a57\u8b49\u554f\u984c",
                audio_challenge: "\u53d6\u5f97\u8a9e\u97f3\u9a57\u8b49\u554f\u984c",
                refresh_btn: "\u53d6\u5f97\u65b0\u7684\u9a57\u8b49\u554f\u984c",
                instructions_visual: "\u8acb\u8f38\u5165\u5716\u7247\u4e2d\u7684\u6587\u5b57\uff1a",
                instructions_audio: "\u8acb\u8f38\u5165\u8a9e\u97f3\u5167\u5bb9\uff1a",
                help_btn: "\u8aaa\u660e",
                play_again: "\u518d\u6b21\u64ad\u653e",
                cant_hear_this: "\u4ee5 MP3 \u683c\u5f0f\u4e0b\u8f09\u8072\u97f3",
                incorrect_try_again: "\u9a57\u8b49\u78bc\u6709\u8aa4\uff0c\u8acb\u518d\u8a66\u4e00\u6b21\u3002",
                image_alt_text: "reCAPTCHA \u9a57\u8b49\u6587\u5b57\u5716\u7247",
                privacy_and_terms: "\u96b1\u79c1\u6b0a\u8207\u689d\u6b3e"
            },
            zu: {
                visual_challenge: "Thola inselelo ebonakalayo",
                audio_challenge: "Thola inselelo yokulalelwayo",
                refresh_btn: "Thola inselelo entsha",
                instructions_visual: "",
                instructions_audio: "Bhala okuzwayo:",
                help_btn: "Usizo",
                play_again: "Phinda udlale okulalelwayo futhi",
                cant_hear_this: "Layisha umsindo njenge-MP3",
                incorrect_try_again: "Akulungile. Zama futhi.",
                image_alt_text: "umfanekiso oyinselelo we-reCAPTCHA",
                privacy_and_terms: "Okwangasese kanye nemigomo"
            },
            tl: ma,
            he: pa,
            "in": oa,
            mo: ra,
            zh: sa
        };
    var ua = function (a, b) {
        for (var c in a) b.call(void 0, a[c], c, a)
    }, va = function (a) {
            var b = [],
                c = 0,
                d;
            for (d in a) b[c++] = a[d];
            return b
        }, wa = function (a) {
            var b = [],
                c = 0,
                d;
            for (d in a) b[c++] = d;
            return b
        }, xa = function (a) {
            for (var b in a) return !1;
            return !0
        }, za = function () {
            var a = ya() ? l.google_ad : null,
                b = {}, c;
            for (c in a) b[c] = a[c];
            return b
        }, Aa = "constructor hasOwnProperty isPrototypeOf propertyIsEnumerable toLocaleString toString valueOf".split(" "),
        Ba = function (a, b) {
            for (var c, d, e = 1; e < arguments.length; e++) {
                d = arguments[e];
                for (c in d) a[c] =
                    d[c];
                for (var g = 0; g < Aa.length; g++) c = Aa[g], Object.prototype.hasOwnProperty.call(d, c) && (a[c] = d[c])
            }
        };
    var u = function (a) {
        if (Error.captureStackTrace) Error.captureStackTrace(this, u);
        else {
            var b = Error().stack;
            b && (this.stack = b)
        }
        a && (this.message = String(a))
    };
    r(u, Error);
    u.prototype.name = "CustomError";
    var Ca;
    var Da = function (a, b) {
        for (var c = a.split("%s"), d = "", e = Array.prototype.slice.call(arguments, 1); e.length && 1 < c.length;) d += c.shift() + e.shift();
        return d + c.join("%s")
    }, x = function (a) {
            if (!Ea.test(a)) return a; - 1 != a.indexOf("&") && (a = a.replace(Fa, "&amp;")); - 1 != a.indexOf("<") && (a = a.replace(Ga, "&lt;")); - 1 != a.indexOf(">") && (a = a.replace(Ha, "&gt;")); - 1 != a.indexOf('"') && (a = a.replace(Ia, "&quot;")); - 1 != a.indexOf("'") && (a = a.replace(Ja, "&#39;"));
            return a
        }, Fa = /&/g,
        Ga = /</g,
        Ha = />/g,
        Ia = /"/g,
        Ja = /'/g,
        Ea = /[&<>"']/,
        Ka = function (a,
            b) {
            return a < b ? -1 : a > b ? 1 : 0
        }, La = function (a) {
            return String(a).replace(/\-([a-z])/g, function (a, c) {
                return c.toUpperCase()
            })
        }, Ma = function (a) {
            var b = n(void 0) ? "undefined".replace(/([-()\[\]{}+?*.$\^|,:#<!\\])/g, "\\$1").replace(/\x08/g, "\\x08") : "\\s";
            return a.replace(RegExp("(^" + (b ? "|[" + b + "]+" : "") + ")([a-z])", "g"), function (a, b, e) {
                return b + e.toUpperCase()
            })
        };
    var Na = function (a, b) {
        b.unshift(a);
        u.call(this, Da.apply(null, b));
        b.shift()
    };
    r(Na, u);
    Na.prototype.name = "AssertionError";
    var y = function (a, b, c) {
        if (!a) {
            var d = "Assertion failed";
            if (b) var d = d + (": " + b),
            e = Array.prototype.slice.call(arguments, 2);
            throw new Na("" + d, e || []);
        }
    }, Oa = function (a, b) {
            throw new Na("Failure" + (a ? ": " + a : ""), Array.prototype.slice.call(arguments, 1));
        };
    var z = Array.prototype,
        Pa = z.indexOf ? function (a, b, c) {
            y(null != a.length);
            return z.indexOf.call(a, b, c)
        } : function (a, b, c) {
            c = null == c ? 0 : 0 > c ? Math.max(0, a.length + c) : c;
            if (n(a)) return n(b) && 1 == b.length ? a.indexOf(b, c) : -1;
            for (; c < a.length; c++)
                if (c in a && a[c] === b) return c;
            return -1
        }, Qa = z.forEach ? function (a, b, c) {
            y(null != a.length);
            z.forEach.call(a, b, c)
        } : function (a, b, c) {
            for (var d = a.length, e = n(a) ? a.split("") : a, g = 0; g < d; g++) g in e && b.call(c, e[g], g, a)
        }, Ra = z.filter ? function (a, b, c) {
            y(null != a.length);
            return z.filter.call(a,
                b, c)
        } : function (a, b, c) {
            for (var d = a.length, e = [], g = 0, f = n(a) ? a.split("") : a, k = 0; k < d; k++)
                if (k in f) {
                    var v = f[k];
                    b.call(c, v, k, a) && (e[g++] = v)
                }
            return e
        }, Sa = z.map ? function (a, b, c) {
            y(null != a.length);
            return z.map.call(a, b, c)
        } : function (a, b, c) {
            for (var d = a.length, e = Array(d), g = n(a) ? a.split("") : a, f = 0; f < d; f++) f in g && (e[f] = b.call(c, g[f], f, a));
            return e
        }, Ua = function (a) {
            var b;
            t: {
                b = Ta;
                for (var c = a.length, d = n(a) ? a.split("") : a, e = 0; e < c; e++)
                    if (e in d && b.call(void 0, d[e], e, a)) {
                        b = e;
                        break t
                    }
                b = -1
            }
            return 0 > b ? null : n(a) ? a.charAt(b) : a[b]
        }, Va = function (a, b) {
            var c = Pa(a, b),
                d;
            if (d = 0 <= c) y(null != a.length), z.splice.call(a, c, 1);
            return d
        }, Wa = function (a) {
            var b = a.length;
            if (0 < b) {
                for (var c = Array(b), d = 0; d < b; d++) c[d] = a[d];
                return c
            }
            return []
        }, Xa = function (a, b, c) {
            y(null != a.length);
            return 2 >= arguments.length ? z.slice.call(a, b) : z.slice.call(a, b, c)
        };
    var A, Ya, Za, $a, ab = function () {
            return l.navigator ? l.navigator.userAgent : null
        };
    $a = Za = Ya = A = !1;
    var B;
    if (B = ab()) {
        var bb = l.navigator;
        A = 0 == B.lastIndexOf("Opera", 0);
        Ya = !A && (-1 != B.indexOf("MSIE") || -1 != B.indexOf("Trident"));
        Za = !A && -1 != B.indexOf("WebKit");
        $a = !A && !Za && !Ya && "Gecko" == bb.product
    }
    var cb = A,
        C = Ya,
        D = $a,
        E = Za,
        db = function () {
            var a = l.document;
            return a ? a.documentMode : void 0
        }, eb;
    t: {
        var fb = "",
            gb;
        if (cb && l.opera) var hb = l.opera.version,
        fb = "function" == typeof hb ? hb() : hb;
        else if (D ? gb = /rv\:([^\);]+)(\)|;)/ : C ? gb = /\b(?:MSIE|rv)[: ]([^\);]+)(\)|;)/ : E && (gb = /WebKit\/(\S+)/), gb) var ib = gb.exec(ab()),
        fb = ib ? ib[1] : "";
        if (C) {
            var jb = db();
            if (jb > parseFloat(fb)) {
                eb = String(jb);
                break t
            }
        }
        eb = fb
    }
    var kb = eb,
        lb = {}, F = function (a) {
            var b;
            if (!(b = lb[a])) {
                b = 0;
                for (var c = String(kb).replace(/^[\s\xa0]+|[\s\xa0]+$/g, "").split("."), d = String(a).replace(/^[\s\xa0]+|[\s\xa0]+$/g, "").split("."), e = Math.max(c.length, d.length), g = 0; 0 == b && g < e; g++) {
                    var f = c[g] || "",
                        k = d[g] || "",
                        v = RegExp("(\\d*)(\\D*)", "g"),
                        M = RegExp("(\\d*)(\\D*)", "g");
                    do {
                        var w = v.exec(f) || ["", "", ""],
                            Q = M.exec(k) || ["", "", ""];
                        if (0 == w[0].length && 0 == Q[0].length) break;
                        b = Ka(0 == w[1].length ? 0 : parseInt(w[1], 10), 0 == Q[1].length ? 0 : parseInt(Q[1], 10)) || Ka(0 == w[2].length,
                            0 == Q[2].length) || Ka(w[2], Q[2])
                    } while (0 == b)
                }
                b = lb[a] = 0 <= b
            }
            return b
        }, mb = l.document,
        nb = mb && C ? db() || ("CSS1Compat" == mb.compatMode ? parseInt(kb, 10) : 5) : void 0;
    var ob = !C || C && 9 <= nb,
        pb = !D && !C || C && C && 9 <= nb || D && F("1.9.1");
    C && F("9");
    var qb = function (a, b) {
        var c;
        c = a.className;
        c = n(c) && c.match(/\S+/g) || [];
        for (var d = Xa(arguments, 1), e = c.length + d.length, g = c, f = 0; f < d.length; f++) 0 <= Pa(g, d[f]) || g.push(d[f]);
        a.className = c.join(" ");
        return c.length == e
    };
    var sb = function (a) {
        return a ? new rb(9 == a.nodeType ? a : a.ownerDocument || a.document) : Ca || (Ca = new rb)
    }, tb = function (a, b) {
            return n(b) ? a.getElementById(b) : b
        }, vb = function (a, b) {
            ua(b, function (b, d) {
                "style" == d ? a.style.cssText = b : "class" == d ? a.className = b : "for" == d ? a.htmlFor = b : d in ub ? a.setAttribute(ub[d], b) : 0 == d.lastIndexOf("aria-", 0) || 0 == d.lastIndexOf("data-", 0) ? a.setAttribute(d, b) : a[d] = b
            })
        }, ub = {
            cellpadding: "cellPadding",
            cellspacing: "cellSpacing",
            colspan: "colSpan",
            frameborder: "frameBorder",
            height: "height",
            maxlength: "maxLength",
            role: "role",
            rowspan: "rowSpan",
            type: "type",
            usemap: "useMap",
            valign: "vAlign",
            width: "width"
        }, xb = function (a, b, c) {
            function d(c) {
                c && b.appendChild(n(c) ? a.createTextNode(c) : c)
            }
            for (var e = 2; e < c.length; e++) {
                var g = c[e];
                !ea(g) || ga(g) && 0 < g.nodeType ? d(g) : Qa(wb(g) ? Wa(g) : g, d)
            }
        }, yb = function (a) {
            for (var b; b = a.firstChild;) a.removeChild(b)
        }, zb = function (a) {
            a && a.parentNode && a.parentNode.removeChild(a)
        }, wb = function (a) {
            if (a && "number" == typeof a.length) {
                if (ga(a)) return "function" == typeof a.item || "string" == typeof a.item;
                if (fa(a)) return "function" ==
                    typeof a.item
            }
            return !1
        }, rb = function (a) {
            this.document_ = a || l.document || document
        };
    h = rb.prototype;
    h.getDomHelper = sb;
    h.getElement = function (a) {
        return tb(this.document_, a)
    };
    h.$ = rb.prototype.getElement;
    h.createDom = function (a, b, c) {
        var d = this.document_,
            e = arguments,
            g = e[0],
            f = e[1];
        if (!ob && f && (f.name || f.type)) {
            g = ["<", g];
            f.name && g.push(' name="', x(f.name), '"');
            if (f.type) {
                g.push(' type="', x(f.type), '"');
                var k = {};
                Ba(k, f);
                delete k.type;
                f = k
            }
            g.push(">");
            g = g.join("")
        }
        g = d.createElement(g);
        f && (n(f) ? g.className = f : m(f) ? qb.apply(null, [g].concat(f)) : vb(g, f));
        2 < e.length && xb(d, g, e);
        return g
    };
    h.createElement = function (a) {
        return this.document_.createElement(a)
    };
    h.createTextNode = function (a) {
        return this.document_.createTextNode(String(a))
    };
    h.appendChild = function (a, b) {
        a.appendChild(b)
    };
    h.getChildren = function (a) {
        return pb && void 0 != a.children ? a.children : Ra(a.childNodes, function (a) {
            return 1 == a.nodeType
        })
    };
    var Ab = function () {};
    Ab.prototype.disposed_ = !1;
    Ab.prototype.dispose = function () {
        this.disposed_ || (this.disposed_ = !0, this.disposeInternal())
    };
    Ab.prototype.disposeInternal = function () {
        if (this.onDisposeCallbacks_)
            for (; this.onDisposeCallbacks_.length;) this.onDisposeCallbacks_.shift()()
    };
    var Bb = function (a) {
        Bb[" "](a);
        return a
    };
    Bb[" "] = ca;
    var Cb = !C || C && 9 <= nb,
        Db = C && !F("9");
    !E || F("528");
    D && F("1.9b") || C && F("8") || cb && F("9.5") || E && F("528");
    D && !F("8") || C && F("9");
    var G = function (a, b) {
        this.type = a;
        this.currentTarget = this.target = b;
        this.defaultPrevented = this.propagationStopped_ = !1;
        this.returnValue_ = !0
    };
    G.prototype.disposeInternal = function () {};
    G.prototype.dispose = function () {};
    G.prototype.preventDefault = function () {
        this.defaultPrevented = !0;
        this.returnValue_ = !1
    };
    var H = function (a, b) {
        G.call(this, a ? a.type : "");
        this.relatedTarget = this.currentTarget = this.target = null;
        this.charCode = this.keyCode = this.button = this.screenY = this.screenX = this.clientY = this.clientX = this.offsetY = this.offsetX = 0;
        this.metaKey = this.shiftKey = this.altKey = this.ctrlKey = !1;
        this.event_ = this.state = null;
        if (a) {
            var c = this.type = a.type;
            this.target = a.target || a.srcElement;
            this.currentTarget = b;
            var d = a.relatedTarget;
            if (d) {
                if (D) {
                    var e;
                    t: {
                        try {
                            Bb(d.nodeName);
                            e = !0;
                            break t
                        } catch (g) {}
                        e = !1
                    }
                    e || (d = null)
                }
            } else "mouseover" ==
                c ? d = a.fromElement : "mouseout" == c && (d = a.toElement);
            this.relatedTarget = d;
            this.offsetX = E || void 0 !== a.offsetX ? a.offsetX : a.layerX;
            this.offsetY = E || void 0 !== a.offsetY ? a.offsetY : a.layerY;
            this.clientX = void 0 !== a.clientX ? a.clientX : a.pageX;
            this.clientY = void 0 !== a.clientY ? a.clientY : a.pageY;
            this.screenX = a.screenX || 0;
            this.screenY = a.screenY || 0;
            this.button = a.button;
            this.keyCode = a.keyCode || 0;
            this.charCode = a.charCode || ("keypress" == c ? a.keyCode : 0);
            this.ctrlKey = a.ctrlKey;
            this.altKey = a.altKey;
            this.shiftKey = a.shiftKey;
            this.metaKey =
                a.metaKey;
            this.state = a.state;
            this.event_ = a;
            a.defaultPrevented && this.preventDefault()
        }
    };
    r(H, G);
    H.prototype.preventDefault = function () {
        H.superClass_.preventDefault.call(this);
        var a = this.event_;
        if (a.preventDefault) a.preventDefault();
        else if (a.returnValue = !1, Db) try {
            if (a.ctrlKey || 112 <= a.keyCode && 123 >= a.keyCode) a.keyCode = -1
        } catch (b) {}
    };
    H.prototype.disposeInternal = function () {};
    var Eb = "closure_listenable_" + (1E6 * Math.random() | 0),
        Fb = function (a) {
            try {
                return !(!a || !a[Eb])
            } catch (b) {
                return !1
            }
        }, Gb = 0;
    var Hb = function (a, b, c, d, e) {
        this.listener = a;
        this.proxy = null;
        this.src = b;
        this.type = c;
        this.capture = !! d;
        this.handler = e;
        this.key = ++Gb;
        this.removed = this.callOnce = !1
    }, Ib = function (a) {
            a.removed = !0;
            a.listener = null;
            a.proxy = null;
            a.src = null;
            a.handler = null
        };
    var I = function (a) {
        this.src = a;
        this.listeners = {};
        this.typeCount_ = 0
    };
    I.prototype.add = function (a, b, c, d, e) {
        var g = this.listeners[a];
        g || (g = this.listeners[a] = [], this.typeCount_++);
        var f = Jb(g, b, d, e); - 1 < f ? (a = g[f], c || (a.callOnce = !1)) : (a = new Hb(b, this.src, a, !! d, e), a.callOnce = c, g.push(a));
        return a
    };
    I.prototype.remove = function (a, b, c, d) {
        if (!(a in this.listeners)) return !1;
        var e = this.listeners[a];
        b = Jb(e, b, c, d);
        return -1 < b ? (Ib(e[b]), y(null != e.length), z.splice.call(e, b, 1), 0 == e.length && (delete this.listeners[a], this.typeCount_--), !0) : !1
    };
    var Kb = function (a, b) {
        var c = b.type;
        if (!(c in a.listeners)) return !1;
        var d = Va(a.listeners[c], b);
        d && (Ib(b), 0 == a.listeners[c].length && (delete a.listeners[c], a.typeCount_--));
        return d
    };
    I.prototype.removeAll = function (a) {
        var b = 0,
            c;
        for (c in this.listeners)
            if (!a || c == a) {
                for (var d = this.listeners[c], e = 0; e < d.length; e++)++b, Ib(d[e]);
                delete this.listeners[c];
                this.typeCount_--
            }
        return b
    };
    I.prototype.getListener = function (a, b, c, d) {
        a = this.listeners[a];
        var e = -1;
        a && (e = Jb(a, b, c, d));
        return -1 < e ? a[e] : null
    };
    var Jb = function (a, b, c, d) {
        for (var e = 0; e < a.length; ++e) {
            var g = a[e];
            if (!g.removed && g.listener == b && g.capture == !! c && g.handler == d) return e
        }
        return -1
    };
    var Lb = "closure_lm_" + (1E6 * Math.random() | 0),
        J = {}, Mb = 0,
        Nb = function (a, b, c, d, e) {
            if (m(b)) {
                for (var g = 0; g < b.length; g++) Nb(a, b[g], c, d, e);
                return null
            }
            c = Ob(c);
            return Fb(a) ? a.listen(b, c, d, e) : Pb(a, b, c, !1, d, e)
        }, Pb = function (a, b, c, d, e, g) {
            if (!b) throw Error("Invalid event type");
            var f = !! e,
                k = Qb(a);
            k || (a[Lb] = k = new I(a));
            c = k.add(b, c, d, e, g);
            if (c.proxy) return c;
            d = Rb();
            c.proxy = d;
            d.src = a;
            d.listener = c;
            a.addEventListener ? a.addEventListener(b, d, f) : a.attachEvent(b in J ? J[b] : J[b] = "on" + b, d);
            Mb++;
            return c
        }, Rb = function () {
            var a =
                Sb,
                b = Cb ? function (c) {
                    return a.call(b.src, b.listener, c)
                } : function (c) {
                    c = a.call(b.src, b.listener, c);
                    if (!c) return c
                };
            return b
        }, Tb = function (a, b, c, d, e) {
            if (m(b)) {
                for (var g = 0; g < b.length; g++) Tb(a, b[g], c, d, e);
                return null
            }
            c = Ob(c);
            return Fb(a) ? a.listenOnce(b, c, d, e) : Pb(a, b, c, !0, d, e)
        }, Ub = function (a, b, c, d, e) {
            if (m(b))
                for (var g = 0; g < b.length; g++) Ub(a, b[g], c, d, e);
            else c = Ob(c), Fb(a) ? a.unlisten(b, c, d, e) : a && (a = Qb(a)) && (b = a.getListener(b, c, !! d, e)) && Vb(b)
        }, Vb = function (a) {
            if ("number" == typeof a || !a || a.removed) return !1;
            var b =
                a.src;
            if (Fb(b)) return Kb(b.eventTargetListeners_, a);
            var c = a.type,
                d = a.proxy;
            b.removeEventListener ? b.removeEventListener(c, d, a.capture) : b.detachEvent && b.detachEvent(c in J ? J[c] : J[c] = "on" + c, d);
            Mb--;
            (c = Qb(b)) ? (Kb(c, a), 0 == c.typeCount_ && (c.src = null, b[Lb] = null)) : Ib(a);
            return !0
        }, Xb = function (a, b, c, d) {
            var e = 1;
            if (a = Qb(a))
                if (b = a.listeners[b])
                    for (b = Wa(b), a = 0; a < b.length; a++) {
                        var g = b[a];
                        g && g.capture == c && !g.removed && (e &= !1 !== Wb(g, d))
                    }
                return Boolean(e)
        }, Wb = function (a, b) {
            var c = a.listener,
                d = a.handler || a.src;
            a.callOnce &&
                Vb(a);
            return c.call(d, b)
        }, Sb = function (a, b) {
            if (a.removed) return !0;
            if (!Cb) {
                var c = b || ba("window.event"),
                    d = new H(c, this),
                    e = !0;
                if (!(0 > c.keyCode || void 0 != c.returnValue)) {
                    t: {
                        var g = !1;
                        if (0 == c.keyCode) try {
                            c.keyCode = -1;
                            break t
                        } catch (f) {
                            g = !0
                        }
                        if (g || void 0 == c.returnValue) c.returnValue = !0
                    }
                    c = [];
                    for (g = d.currentTarget; g; g = g.parentNode) c.push(g);
                    for (var g = a.type, k = c.length - 1; !d.propagationStopped_ && 0 <= k; k--) d.currentTarget = c[k], e &= Xb(c[k], g, !0, d);
                    for (k = 0; !d.propagationStopped_ && k < c.length; k++) d.currentTarget = c[k],
                    e &= Xb(c[k], g, !1, d)
                }
                return e
            }
            return Wb(a, new H(b, this))
        }, Qb = function (a) {
            a = a[Lb];
            return a instanceof I ? a : null
        }, Yb = "__closure_events_fn_" + (1E9 * Math.random() >>> 0),
        Ob = function (a) {
            y(a, "Listener can not be null.");
            if (fa(a)) return a;
            y(a.handleEvent, "An object listener must have handleEvent method.");
            return a[Yb] || (a[Yb] = function (b) {
                return a.handleEvent(b)
            })
        };
    var K = function (a) {
        this.handler_ = a;
        this.keys_ = {}
    };
    r(K, Ab);
    var Zb = [];
    K.prototype.listen = function (a, b, c, d) {
        m(b) || (Zb[0] = b, b = Zb);
        for (var e = 0; e < b.length; e++) {
            var g = Nb(a, b[e], c || this.handleEvent, d || !1, this.handler_ || this);
            if (!g) break;
            this.keys_[g.key] = g
        }
        return this
    };
    K.prototype.listenOnce = function (a, b, c, d) {
        return $b(this, a, b, c, d)
    };
    var $b = function (a, b, c, d, e, g) {
        if (m(c))
            for (var f = 0; f < c.length; f++) $b(a, b, c[f], d, e, g);
        else {
            b = Tb(b, c, d || a.handleEvent, e, g || a.handler_ || a);
            if (!b) return a;
            a.keys_[b.key] = b
        }
        return a
    };
    K.prototype.unlisten = function (a, b, c, d, e) {
        if (m(b))
            for (var g = 0; g < b.length; g++) this.unlisten(a, b[g], c, d, e);
        else c = c || this.handleEvent, e = e || this.handler_ || this, c = Ob(c), d = !! d, b = Fb(a) ? a.getListener(b, c, d, e) : a ? (a = Qb(a)) ? a.getListener(b, c, d, e) : null : null, b && (Vb(b), delete this.keys_[b.key]);
        return this
    };
    K.prototype.removeAll = function () {
        ua(this.keys_, Vb);
        this.keys_ = {}
    };
    K.prototype.disposeInternal = function () {
        K.superClass_.disposeInternal.call(this);
        this.removeAll()
    };
    K.prototype.handleEvent = function () {
        throw Error("EventHandler.handleEvent not implemented");
    };
    var L = function () {
        this.eventTargetListeners_ = new I(this);
        this.actualEventTarget_ = this
    };
    r(L, Ab);
    L.prototype[Eb] = !0;
    h = L.prototype;
    h.parentEventTarget_ = null;
    h.setParentEventTarget = function (a) {
        this.parentEventTarget_ = a
    };
    h.addEventListener = function (a, b, c, d) {
        Nb(this, a, b, c, d)
    };
    h.removeEventListener = function (a, b, c, d) {
        Ub(this, a, b, c, d)
    };
    h.dispatchEvent = function (a) {
        ac(this);
        var b, c = this.parentEventTarget_;
        if (c) {
            b = [];
            for (var d = 1; c; c = c.parentEventTarget_) b.push(c), y(1E3 > ++d, "infinite loop")
        }
        c = this.actualEventTarget_;
        d = a.type || a;
        if (n(a)) a = new G(a, c);
        else if (a instanceof G) a.target = a.target || c;
        else {
            var e = a;
            a = new G(d, c);
            Ba(a, e)
        }
        var e = !0,
            g;
        if (b)
            for (var f = b.length - 1; !a.propagationStopped_ && 0 <= f; f--) g = a.currentTarget = b[f], e = bc(g, d, !0, a) && e;
        a.propagationStopped_ || (g = a.currentTarget = c, e = bc(g, d, !0, a) && e, a.propagationStopped_ || (e = bc(g, d, !1, a) &&
            e));
        if (b)
            for (f = 0; !a.propagationStopped_ && f < b.length; f++) g = a.currentTarget = b[f], e = bc(g, d, !1, a) && e;
        return e
    };
    h.disposeInternal = function () {
        L.superClass_.disposeInternal.call(this);
        this.eventTargetListeners_ && this.eventTargetListeners_.removeAll(void 0);
        this.parentEventTarget_ = null
    };
    h.listen = function (a, b, c, d) {
        ac(this);
        return this.eventTargetListeners_.add(String(a), b, !1, c, d)
    };
    h.listenOnce = function (a, b, c, d) {
        return this.eventTargetListeners_.add(String(a), b, !0, c, d)
    };
    h.unlisten = function (a, b, c, d) {
        return this.eventTargetListeners_.remove(String(a), b, c, d)
    };
    var bc = function (a, b, c, d) {
        b = a.eventTargetListeners_.listeners[String(b)];
        if (!b) return !0;
        b = Wa(b);
        for (var e = !0, g = 0; g < b.length; ++g) {
            var f = b[g];
            if (f && !f.removed && f.capture == c) {
                var k = f.listener,
                    v = f.handler || f.src;
                f.callOnce && Kb(a.eventTargetListeners_, f);
                e = !1 !== k.call(v, d) && e
            }
        }
        return e && !1 != d.returnValue_
    };
    L.prototype.getListener = function (a, b, c, d) {
        return this.eventTargetListeners_.getListener(String(a), b, c, d)
    };
    var ac = function (a) {
        y(a.eventTargetListeners_, "Event target is not initialized. Did you call the superclass (goog.events.EventTarget) constructor?")
    };
    var N = function (a) {
        L.call(this);
        this.imageIdToRequestMap_ = {};
        this.imageIdToImageMap_ = {};
        this.handler_ = new K(this);
        this.parent_ = a
    };
    r(N, L);
    var cc = [C && !F("11") ? "readystatechange" : "load", "abort", "error"],
        dc = function (a, b, c) {
            (c = n(c) ? c : c.src) && (a.imageIdToRequestMap_[b] = {
                src: c,
                corsRequestType: null
            })
        };
    N.prototype.start = function () {
        var a = this.imageIdToRequestMap_;
        Qa(wa(a), function (b) {
            var c = a[b];
            if (c && (delete a[b], !this.disposed_)) {
                var d;
                d = this.parent_ ? sb(this.parent_).createDom("img") : new Image;
                c.corsRequestType && (d.crossOrigin = c.corsRequestType);
                this.handler_.listen(d, cc, this.onNetworkEvent_);
                this.imageIdToImageMap_[b] = d;
                d.id = b;
                d.src = c.src
            }
        }, this)
    };
    N.prototype.onNetworkEvent_ = function (a) {
        var b = a.currentTarget;
        if (b) {
            if ("readystatechange" == a.type)
                if ("complete" == b.readyState) a.type = "load";
                else return;
                "undefined" == typeof b.naturalWidth && ("load" == a.type ? (b.naturalWidth = b.width, b.naturalHeight = b.height) : (b.naturalWidth = 0, b.naturalHeight = 0));
            this.dispatchEvent({
                type: a.type,
                target: b
            });
            !this.disposed_ && (a = b.id, delete this.imageIdToRequestMap_[a], b = this.imageIdToImageMap_[a]) && (delete this.imageIdToImageMap_[a], this.handler_.unlisten(b, cc, this.onNetworkEvent_),
                xa(this.imageIdToImageMap_) && xa(this.imageIdToRequestMap_) && this.dispatchEvent("complete"))
        }
    };
    N.prototype.disposeInternal = function () {
        delete this.imageIdToRequestMap_;
        delete this.imageIdToImageMap_;
        var a = this.handler_;
        a && "function" == typeof a.dispose && a.dispose();
        N.superClass_.disposeInternal.call(this)
    };
    var ec = "StopIteration" in l ? l.StopIteration : Error("StopIteration"),
        fc = function () {};
    fc.prototype.next = function () {
        throw ec;
    };
    fc.prototype.__iterator__ = function () {
        return this
    };
    var O = function (a, b) {
        this.map_ = {};
        this.keys_ = [];
        this.version_ = this.count_ = 0;
        var c = arguments.length;
        if (1 < c) {
            if (c % 2) throw Error("Uneven number of arguments");
            for (var d = 0; d < c; d += 2) this.set(arguments[d], arguments[d + 1])
        } else if (a) {
            a instanceof O ? (c = a.getKeys(), d = a.getValues()) : (c = wa(a), d = va(a));
            for (var e = 0; e < c.length; e++) this.set(c[e], d[e])
        }
    };
    O.prototype.getValues = function () {
        gc(this);
        for (var a = [], b = 0; b < this.keys_.length; b++) a.push(this.map_[this.keys_[b]]);
        return a
    };
    O.prototype.getKeys = function () {
        gc(this);
        return this.keys_.concat()
    };
    O.prototype.remove = function (a) {
        return Object.prototype.hasOwnProperty.call(this.map_, a) ? (delete this.map_[a], this.count_--, this.version_++, this.keys_.length > 2 * this.count_ && gc(this), !0) : !1
    };
    var gc = function (a) {
        if (a.count_ != a.keys_.length) {
            for (var b = 0, c = 0; b < a.keys_.length;) {
                var d = a.keys_[b];
                Object.prototype.hasOwnProperty.call(a.map_, d) && (a.keys_[c++] = d);
                b++
            }
            a.keys_.length = c
        }
        if (a.count_ != a.keys_.length) {
            for (var e = {}, c = b = 0; b < a.keys_.length;) d = a.keys_[b], Object.prototype.hasOwnProperty.call(e, d) || (a.keys_[c++] = d, e[d] = 1), b++;
            a.keys_.length = c
        }
    };
    O.prototype.set = function (a, b) {
        Object.prototype.hasOwnProperty.call(this.map_, a) || (this.count_++, this.keys_.push(a), this.version_++);
        this.map_[a] = b
    };
    O.prototype.__iterator__ = function (a) {
        gc(this);
        var b = 0,
            c = this.keys_,
            d = this.map_,
            e = this.version_,
            g = this,
            f = new fc;
        f.next = function () {
            for (;;) {
                if (e != g.version_) throw Error("The map has changed since the iterator was created");
                if (b >= c.length) throw ec;
                var f = c[b++];
                return a ? f : d[f]
            }
        };
        return f
    };
    var hc = function (a) {
        if ("function" == typeof a.getValues) return a.getValues();
        if (n(a)) return a.split("");
        if (ea(a)) {
            for (var b = [], c = a.length, d = 0; d < c; d++) b.push(a[d]);
            return b
        }
        return va(a)
    }, ic = function (a, b, c) {
            if ("function" == typeof a.forEach) a.forEach(b, c);
            else if (ea(a) || n(a)) Qa(a, b, c);
            else {
                var d;
                if ("function" == typeof a.getKeys) d = a.getKeys();
                else if ("function" != typeof a.getValues)
                    if (ea(a) || n(a)) {
                        d = [];
                        for (var e = a.length, g = 0; g < e; g++) d.push(g)
                    } else d = wa(a);
                    else d = void 0;
                for (var e = hc(a), g = e.length, f = 0; f < g; f++) b.call(c,
                    e[f], d && d[f], a)
            }
        };
    var kc = function (a) {
        return jc(a || arguments.callee.caller, [])
    }, jc = function (a, b) {
            var c = [];
            if (0 <= Pa(b, a)) c.push("[...circular reference...]");
            else if (a && 50 > b.length) {
                c.push(lc(a) + "(");
                for (var d = a.arguments, e = 0; e < d.length; e++) {
                    0 < e && c.push(", ");
                    var g;
                    g = d[e];
                    switch (typeof g) {
                    case "object":
                        g = g ? "object" : "null";
                        break;
                    case "string":
                        break;
                    case "number":
                        g = String(g);
                        break;
                    case "boolean":
                        g = g ? "true" : "false";
                        break;
                    case "function":
                        g = (g = lc(g)) ? g : "[fn]";
                        break;
                    default:
                        g = typeof g
                    }
                    40 < g.length && (g = g.substr(0, 40) + "...");
                    c.push(g)
                }
                b.push(a);
                c.push(")\n");
                try {
                    c.push(jc(a.caller, b))
                } catch (f) {
                    c.push("[exception trying to get caller]\n")
                }
            } else a ? c.push("[...long stack...]") : c.push("[end]");
            return c.join("")
        }, lc = function (a) {
            if (mc[a]) return mc[a];
            a = String(a);
            if (!mc[a]) {
                var b = /function ([^\(]+)/.exec(a);
                mc[a] = b ? b[1] : "[Anonymous]"
            }
            return mc[a]
        }, mc = {};
    var nc = function (a, b, c, d, e) {
        this.reset(a, b, c, d, e)
    };
    nc.prototype.exception_ = null;
    nc.prototype.exceptionText_ = null;
    var oc = 0;
    nc.prototype.reset = function (a, b, c, d, e) {
        "number" == typeof e || oc++;
        d || ja();
        this.level_ = a;
        this.msg_ = b;
        delete this.exception_;
        delete this.exceptionText_
    };
    nc.prototype.setLevel = function (a) {
        this.level_ = a
    };
    var P = function (a) {
        this.name_ = a
    };
    P.prototype.parent_ = null;
    P.prototype.level_ = null;
    P.prototype.children_ = null;
    P.prototype.handlers_ = null;
    var pc = function (a, b) {
        this.name = a;
        this.value = b
    };
    pc.prototype.toString = function () {
        return this.name
    };
    var qc = new pc("SEVERE", 1E3),
        rc = new pc("WARNING", 900),
        sc = new pc("CONFIG", 700),
        tc = new pc("FINE", 500);
    P.prototype.getParent = function () {
        return this.parent_
    };
    P.prototype.getChildren = function () {
        this.children_ || (this.children_ = {});
        return this.children_
    };
    P.prototype.setLevel = function (a) {
        this.level_ = a
    };
    var uc = function (a) {
        if (a.level_) return a.level_;
        if (a.parent_) return uc(a.parent_);
        Oa("Root logger has no level set.");
        return null
    };
    P.prototype.log = function (a, b, c) {
        if (a.value >= uc(this).value)
            for (fa(b) && (b = b()), a = this.getLogRecord(a, b, c), b = "log:" + a.msg_, l.console && (l.console.timeStamp ? l.console.timeStamp(b) : l.console.markTimeline && l.console.markTimeline(b)), l.msWriteProfilerMark && l.msWriteProfilerMark(b), b = this; b;) {
                c = b;
                var d = a;
                if (c.handlers_)
                    for (var e = 0, g = void 0; g = c.handlers_[e]; e++) g(d);
                b = b.getParent()
            }
    };
    P.prototype.getLogRecord = function (a, b, c) {
        var d = new nc(a, String(b), this.name_);
        if (c) {
            d.exception_ = c;
            var e;
            var g = arguments.callee.caller;
            try {
                var f;
                var k = ba("window.location.href");
                if (n(c)) f = {
                    message: c,
                    name: "Unknown error",
                    lineNumber: "Not available",
                    fileName: k,
                    stack: "Not available"
                };
                else {
                    var v, M, w = !1;
                    try {
                        v = c.lineNumber || c.line || "Not available"
                    } catch (Q) {
                        v = "Not available", w = !0
                    }
                    try {
                        M = c.fileName || c.filename || c.sourceURL || l.$googDebugFname || k
                    } catch (kd) {
                        M = "Not available", w = !0
                    }
                    f = !w && c.lineNumber && c.fileName &&
                        c.stack && c.message && c.name ? c : {
                            message: c.message || "Not available",
                            name: c.name || "UnknownError",
                            lineNumber: v,
                            fileName: M,
                            stack: c.stack || "Not available"
                    }
                }
                e = "Message: " + x(f.message) + '\nUrl: <a href="view-source:' + f.fileName + '" target="_new">' + f.fileName + "</a>\nLine: " + f.lineNumber + "\n\nBrowser stack:\n" + x(f.stack + "-> ") + "[end]\n\nJS stack traversal:\n" + x(kc(g) + "-> ")
            } catch (Zc) {
                e = "Exception trying to expose exception! You win, we lose. " + Zc
            }
            d.exceptionText_ = e
        }
        return d
    };
    var vc = {}, wc = null,
        xc = function (a) {
            wc || (wc = new P(""), vc[""] = wc, wc.setLevel(sc));
            var b;
            if (!(b = vc[a])) {
                b = new P(a);
                var c = a.lastIndexOf("."),
                    d = a.substr(c + 1),
                    c = xc(a.substr(0, c));
                c.getChildren()[d] = b;
                b.parent_ = c;
                vc[a] = b
            }
            return b
        };
    var R = function (a, b) {
        a && a.log(tc, b, void 0)
    };
    var yc = function (a, b, c) {
        if (fa(a)) c && (a = p(a, c));
        else if (a && "function" == typeof a.handleEvent) a = p(a.handleEvent, a);
        else throw Error("Invalid listener argument");
        return 2147483647 < b ? -1 : l.setTimeout(a, b || 0)
    };
    var zc = RegExp("^(?:([^:/?#.]+):)?(?://(?:([^/?#]*)@)?([^/#?]*?)(?::([0-9]+))?(?=[/#?]|$))?([^?#]+)?(?:\\?([^#]*))?(?:#(.*))?$"),
        Ac = E,
        Bc = function (a, b) {
            if (Ac) {
                Ac = !1;
                var c = l.location;
                if (c) {
                    var d = c.href;
                    if (d && (d = (d = Bc(3, d)) && decodeURIComponent(d)) && d != c.hostname) throw Ac = !0, Error();
                }
            }
            return b.match(zc)[a] || null
        };
    var Cc = function () {};
    Cc.prototype.cachedOptions_ = null;
    var Ec = function (a) {
        var b;
        (b = a.cachedOptions_) || (b = {}, Dc(a) && (b[0] = !0, b[1] = !0), b = a.cachedOptions_ = b);
        return b
    };
    var Fc, Gc = function () {};
    r(Gc, Cc);
    var Hc = function (a) {
        return (a = Dc(a)) ? new ActiveXObject(a) : new XMLHttpRequest
    }, Dc = function (a) {
            if (!a.ieProgId_ && "undefined" == typeof XMLHttpRequest && "undefined" != typeof ActiveXObject) {
                for (var b = ["MSXML2.XMLHTTP.6.0", "MSXML2.XMLHTTP.3.0", "MSXML2.XMLHTTP", "Microsoft.XMLHTTP"], c = 0; c < b.length; c++) {
                    var d = b[c];
                    try {
                        return new ActiveXObject(d), a.ieProgId_ = d
                    } catch (e) {}
                }
                throw Error("Could not create ActiveXObject. ActiveX might be disabled, or MSXML might not be installed");
            }
            return a.ieProgId_
        };
    Fc = new Gc;
    var S = function (a) {
        L.call(this);
        this.headers = new O;
        this.xmlHttpFactory_ = a || null;
        this.active_ = !1;
        this.xhrOptions_ = this.xhr_ = null;
        this.lastError_ = this.lastMethod_ = this.lastUri_ = "";
        this.inAbort_ = this.inOpen_ = this.inSend_ = this.errorDispatched_ = !1;
        this.timeoutInterval_ = 0;
        this.timeoutId_ = null;
        this.responseType_ = "";
        this.useXhr2Timeout_ = this.withCredentials_ = !1
    };
    r(S, L);
    var Ic = S.prototype,
        Jc = xc("goog.net.XhrIo");
    Ic.logger_ = Jc;
    var Kc = /^https?$/i,
        Lc = ["POST", "PUT"],
        Mc = [],
        Nc = function (a) {
            var b = new S;
            Mc.push(b);
            b.listenOnce("ready", b.cleanupSend_);
            b.send(a, "POST", void 0, void 0)
        };
    S.prototype.cleanupSend_ = function () {
        this.dispose();
        Va(Mc, this)
    };
    S.prototype.send = function (a, b, c, d) {
        if (this.xhr_) throw Error("[goog.net.XhrIo] Object is active with another request=" + this.lastUri_ + "; newUri=" + a);
        b = b ? b.toUpperCase() : "GET";
        this.lastUri_ = a;
        this.lastError_ = "";
        this.lastMethod_ = b;
        this.errorDispatched_ = !1;
        this.active_ = !0;
        this.xhr_ = this.xmlHttpFactory_ ? Hc(this.xmlHttpFactory_) : Hc(Fc);
        this.xhrOptions_ = this.xmlHttpFactory_ ? Ec(this.xmlHttpFactory_) : Ec(Fc);
        this.xhr_.onreadystatechange = p(this.onReadyStateChange_, this);
        try {
            R(this.logger_, T(this, "Opening Xhr")),
            this.inOpen_ = !0, this.xhr_.open(b, String(a), !0), this.inOpen_ = !1
        } catch (e) {
            R(this.logger_, T(this, "Error opening Xhr: " + e.message));
            Oc(this, e);
            return
        }
        a = c || "";
        var g = new O(this.headers);
        d && ic(d, function (a, b) {
            g.set(b, a)
        });
        d = Ua(g.getKeys());
        c = l.FormData && a instanceof l.FormData;
        !(0 <= Pa(Lc, b)) || d || c || g.set("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        ic(g, function (a, b) {
            this.xhr_.setRequestHeader(b, a)
        }, this);
        this.responseType_ && (this.xhr_.responseType = this.responseType_);
        "withCredentials" in
            this.xhr_ && (this.xhr_.withCredentials = this.withCredentials_);
        try {
            Pc(this), 0 < this.timeoutInterval_ && (this.useXhr2Timeout_ = Qc(this.xhr_), R(this.logger_, T(this, "Will abort after " + this.timeoutInterval_ + "ms if incomplete, xhr2 " + this.useXhr2Timeout_)), this.useXhr2Timeout_ ? (this.xhr_.timeout = this.timeoutInterval_, this.xhr_.ontimeout = p(this.timeout_, this)) : this.timeoutId_ = yc(this.timeout_, this.timeoutInterval_, this)), R(this.logger_, T(this, "Sending request")), this.inSend_ = !0, this.xhr_.send(a), this.inSend_ = !1
        } catch (f) {
            R(this.logger_, T(this, "Send error: " + f.message)), Oc(this, f)
        }
    };
    var Qc = function (a) {
        return C && F(9) && "number" == typeof a.timeout && void 0 !== a.ontimeout
    }, Ta = function (a) {
            return "content-type" == a.toLowerCase()
        };
    S.prototype.timeout_ = function () {
        "undefined" != typeof aa && this.xhr_ && (this.lastError_ = "Timed out after " + this.timeoutInterval_ + "ms, aborting", R(this.logger_, T(this, this.lastError_)), this.dispatchEvent("timeout"), this.abort(8))
    };
    var Oc = function (a, b) {
        a.active_ = !1;
        a.xhr_ && (a.inAbort_ = !0, a.xhr_.abort(), a.inAbort_ = !1);
        a.lastError_ = b;
        Rc(a);
        Sc(a)
    }, Rc = function (a) {
            a.errorDispatched_ || (a.errorDispatched_ = !0, a.dispatchEvent("complete"), a.dispatchEvent("error"))
        };
    S.prototype.abort = function () {
        this.xhr_ && this.active_ && (R(this.logger_, T(this, "Aborting")), this.active_ = !1, this.inAbort_ = !0, this.xhr_.abort(), this.inAbort_ = !1, this.dispatchEvent("complete"), this.dispatchEvent("abort"), Sc(this))
    };
    S.prototype.disposeInternal = function () {
        this.xhr_ && (this.active_ && (this.active_ = !1, this.inAbort_ = !0, this.xhr_.abort(), this.inAbort_ = !1), Sc(this, !0));
        S.superClass_.disposeInternal.call(this)
    };
    S.prototype.onReadyStateChange_ = function () {
        if (!this.disposed_)
            if (this.inOpen_ || this.inSend_ || this.inAbort_) Tc(this);
            else this.onReadyStateChangeEntryPoint_()
    };
    S.prototype.onReadyStateChangeEntryPoint_ = function () {
        Tc(this)
    };
    var Tc = function (a) {
        if (a.active_ && "undefined" != typeof aa)
            if (a.xhrOptions_[1] && 4 == Uc(a) && 2 == Vc(a)) R(a.logger_, T(a, "Local request error detected and ignored"));
            else if (a.inSend_ && 4 == Uc(a)) yc(a.onReadyStateChange_, 0, a);
        else if (a.dispatchEvent("readystatechange"), 4 == Uc(a)) {
            R(a.logger_, T(a, "Request complete"));
            a.active_ = !1;
            try {
                var b = Vc(a),
                    c, d;
                t: switch (b) {
                case 200:
                case 201:
                case 202:
                case 204:
                case 206:
                case 304:
                case 1223:
                    d = !0;
                    break t;
                default:
                    d = !1
                }
                if (!(c = d)) {
                    var e;
                    if (e = 0 === b) {
                        var g = Bc(1, String(a.lastUri_));
                        if (!g && self.location) var f = self.location.protocol,
                        g = f.substr(0, f.length - 1);
                        e = !Kc.test(g ? g.toLowerCase() : "")
                    }
                    c = e
                }
                if (c) a.dispatchEvent("complete"), a.dispatchEvent("success");
                else {
                    var k;
                    try {
                        k = 2 < Uc(a) ? a.xhr_.statusText : ""
                    } catch (v) {
                        R(a.logger_, "Can not get status: " + v.message), k = ""
                    }
                    a.lastError_ = k + " [" + Vc(a) + "]";
                    Rc(a)
                }
            } finally {
                Sc(a)
            }
        }
    }, Sc = function (a, b) {
            if (a.xhr_) {
                Pc(a);
                var c = a.xhr_,
                    d = a.xhrOptions_[0] ? ca : null;
                a.xhr_ = null;
                a.xhrOptions_ = null;
                b || a.dispatchEvent("ready");
                try {
                    c.onreadystatechange = d
                } catch (e) {
                    (c =
                        a.logger_) && c.log(qc, "Problem encountered resetting onreadystatechange: " + e.message, void 0)
                }
            }
        }, Pc = function (a) {
            a.xhr_ && a.useXhr2Timeout_ && (a.xhr_.ontimeout = null);
            "number" == typeof a.timeoutId_ && (l.clearTimeout(a.timeoutId_), a.timeoutId_ = null)
        }, Uc = function (a) {
            return a.xhr_ ? a.xhr_.readyState : 0
        }, Vc = function (a) {
            try {
                return 2 < Uc(a) ? a.xhr_.status : -1
            } catch (b) {
                return (a = a.logger_) && a.log(rc, "Can not get status: " + b.message, void 0), -1
            }
        }, T = function (a, b) {
            return b + " [" + a.lastMethod_ + " " + a.lastUri_ + " " + Vc(a) + "]"
        };
    var U = function () {};
    U.getInstance = function () {
        return U.instance_ ? U.instance_ : U.instance_ = new U
    };
    U.prototype.nextId_ = 0;
    var V = function (a) {
        L.call(this);
        this.dom_ = a || sb()
    };
    r(V, L);
    h = V.prototype;
    h.idGenerator_ = U.getInstance();
    h.id_ = null;
    h.inDocument_ = !1;
    h.element_ = null;
    h.parent_ = null;
    h.children_ = null;
    h.childIndex_ = null;
    h.wasDecorated_ = !1;
    h.getElement = function () {
        return this.element_
    };
    h.getParent = function () {
        return this.parent_
    };
    h.setParentEventTarget = function (a) {
        if (this.parent_ && this.parent_ != a) throw Error("Method not supported");
        V.superClass_.setParentEventTarget.call(this, a)
    };
    h.getDomHelper = function () {
        return this.dom_
    };
    h.createDom = function () {
        this.element_ = this.dom_.createElement("div")
    };
    var Xc = function (a, b) {
        if (a.inDocument_) throw Error("Component already rendered");
        a.element_ || a.createDom();
        b ? b.insertBefore(a.element_, null) : a.dom_.document_.body.appendChild(a.element_);
        a.parent_ && !a.parent_.inDocument_ || Wc(a)
    }, Wc = function (a) {
            a.inDocument_ = !0;
            Yc(a, function (a) {
                !a.inDocument_ && a.getElement() && Wc(a)
            })
        }, $c = function (a) {
            Yc(a, function (a) {
                a.inDocument_ && $c(a)
            });
            a.googUiComponentHandler_ && a.googUiComponentHandler_.removeAll();
            a.inDocument_ = !1
        };
    V.prototype.disposeInternal = function () {
        this.inDocument_ && $c(this);
        this.googUiComponentHandler_ && (this.googUiComponentHandler_.dispose(), delete this.googUiComponentHandler_);
        Yc(this, function (a) {
            a.dispose()
        });
        !this.wasDecorated_ && this.element_ && zb(this.element_);
        this.parent_ = this.element_ = this.childIndex_ = this.children_ = null;
        V.superClass_.disposeInternal.call(this)
    };
    var Yc = function (a, b) {
        a.children_ && Qa(a.children_, b, void 0)
    };
    V.prototype.removeChild = function (a, b) {
        if (a) {
            var c = n(a) ? a : a.id_ || (a.id_ = ":" + (a.idGenerator_.nextId_++).toString(36)),
                d;
            this.childIndex_ && c ? (d = this.childIndex_, d = (c in d ? d[c] : void 0) || null) : d = null;
            a = d;
            if (c && a) {
                d = this.childIndex_;
                c in d && delete d[c];
                Va(this.children_, a);
                b && ($c(a), a.element_ && zb(a.element_));
                c = a;
                if (null == c) throw Error("Unable to set parent component");
                c.parent_ = null;
                V.superClass_.setParentEventTarget.call(c, null)
            }
        }
        if (!a) throw Error("Child is not in parent component");
        return a
    };
    var W = function (a, b, c) {
        V.call(this, c);
        this.captchaImage_ = a;
        this.adImage_ = b && 300 == b.naturalWidth && 57 == b.naturalHeight ? b : null
    };
    r(W, V);
    W.prototype.createDom = function () {
        W.superClass_.createDom.call(this);
        var a = this.getElement();
        this.captchaImage_.alt = X.image_alt_text;
        this.getDomHelper().appendChild(a, this.captchaImage_);
        this.adImage_ && (this.adImage_.alt = X.image_alt_text, this.getDomHelper().appendChild(a, this.adImage_), this.adImage_ && ad(this.adImage_) && (a.innerHTML += '<div id="recaptcha-ad-choices"><div class="recaptcha-ad-choices-collapsed"><img height="15" width="15" alt="AdChoices" border="0" src="//pagead2.googlesyndication.com/pagead/images/adchoices/icon.png"/></div><div class="recaptcha-ad-choices-expanded"><a href="https://support.google.com/adsense/troubleshooter/1631343" target="_blank"><img height="15" width="75" alt="AdChoices" border="0" src="//pagead2.googlesyndication.com/pagead/images/adchoices/en.png"/></a></div></div>'))
    };
    var ad = function (a) {
        var b = bd(a, "visibility");
        a = bd(a, "display");
        return "hidden" != b && "none" != a
    }, bd = function (a, b) {
            var c;
            t: {
                c = 9 == a.nodeType ? a : a.ownerDocument || a.document;
                if (c.defaultView && c.defaultView.getComputedStyle && (c = c.defaultView.getComputedStyle(a, null))) {
                    c = c[b] || c.getPropertyValue(b) || "";
                    break t
                }
                c = ""
            }
            if (!c && !(c = a.currentStyle ? a.currentStyle[b] : null) && (c = a.style[La(b)], "undefined" === typeof c)) {
                c = a.style;
                var d;
                t: if (d = La(b), void 0 === a.style[d]) {
                    var e = (E ? "Webkit" : D ? "Moz" : C ? "ms" : cb ? "O" : null) + Ma(b);
                    if (void 0 !== a.style[e]) {
                        d = e;
                        break t
                    }
                }
                c = c[d] || ""
            }
            return c
        };
    W.prototype.disposeInternal = function () {
        delete this.captchaImage_;
        delete this.adImage_;
        W.superClass_.disposeInternal.call(this)
    };
    var cd = function (a) {
        return Sa(a, function (a) {
            a = a.toString(16);
            return 1 < a.length ? a : "0" + a
        }).join("")
    };
    var dd = function () {
        this.blockSize = -1
    };
    var ed = function () {
        this.blockSize = -1;
        this.blockSize = 64;
        this.chain_ = Array(4);
        this.block_ = Array(this.blockSize);
        this.totalLength_ = this.blockLength_ = 0;
        this.reset()
    };
    r(ed, dd);
    ed.prototype.reset = function () {
        this.chain_[0] = 1732584193;
        this.chain_[1] = 4023233417;
        this.chain_[2] = 2562383102;
        this.chain_[3] = 271733878;
        this.totalLength_ = this.blockLength_ = 0
    };
    var fd = function (a, b, c) {
        c || (c = 0);
        var d = Array(16);
        if (n(b))
            for (var e = 0; 16 > e; ++e) d[e] = b.charCodeAt(c++) | b.charCodeAt(c++) << 8 | b.charCodeAt(c++) << 16 | b.charCodeAt(c++) << 24;
        else
            for (e = 0; 16 > e; ++e) d[e] = b[c++] | b[c++] << 8 | b[c++] << 16 | b[c++] << 24;
        b = a.chain_[0];
        c = a.chain_[1];
        var e = a.chain_[2],
            g = a.chain_[3],
            f = 0,
            f = b + (g ^ c & (e ^ g)) + d[0] + 3614090360 & 4294967295;
        b = c + (f << 7 & 4294967295 | f >>> 25);
        f = g + (e ^ b & (c ^ e)) + d[1] + 3905402710 & 4294967295;
        g = b + (f << 12 & 4294967295 | f >>> 20);
        f = e + (c ^ g & (b ^ c)) + d[2] + 606105819 & 4294967295;
        e = g + (f << 17 & 4294967295 |
            f >>> 15);
        f = c + (b ^ e & (g ^ b)) + d[3] + 3250441966 & 4294967295;
        c = e + (f << 22 & 4294967295 | f >>> 10);
        f = b + (g ^ c & (e ^ g)) + d[4] + 4118548399 & 4294967295;
        b = c + (f << 7 & 4294967295 | f >>> 25);
        f = g + (e ^ b & (c ^ e)) + d[5] + 1200080426 & 4294967295;
        g = b + (f << 12 & 4294967295 | f >>> 20);
        f = e + (c ^ g & (b ^ c)) + d[6] + 2821735955 & 4294967295;
        e = g + (f << 17 & 4294967295 | f >>> 15);
        f = c + (b ^ e & (g ^ b)) + d[7] + 4249261313 & 4294967295;
        c = e + (f << 22 & 4294967295 | f >>> 10);
        f = b + (g ^ c & (e ^ g)) + d[8] + 1770035416 & 4294967295;
        b = c + (f << 7 & 4294967295 | f >>> 25);
        f = g + (e ^ b & (c ^ e)) + d[9] + 2336552879 & 4294967295;
        g = b + (f << 12 & 4294967295 |
            f >>> 20);
        f = e + (c ^ g & (b ^ c)) + d[10] + 4294925233 & 4294967295;
        e = g + (f << 17 & 4294967295 | f >>> 15);
        f = c + (b ^ e & (g ^ b)) + d[11] + 2304563134 & 4294967295;
        c = e + (f << 22 & 4294967295 | f >>> 10);
        f = b + (g ^ c & (e ^ g)) + d[12] + 1804603682 & 4294967295;
        b = c + (f << 7 & 4294967295 | f >>> 25);
        f = g + (e ^ b & (c ^ e)) + d[13] + 4254626195 & 4294967295;
        g = b + (f << 12 & 4294967295 | f >>> 20);
        f = e + (c ^ g & (b ^ c)) + d[14] + 2792965006 & 4294967295;
        e = g + (f << 17 & 4294967295 | f >>> 15);
        f = c + (b ^ e & (g ^ b)) + d[15] + 1236535329 & 4294967295;
        c = e + (f << 22 & 4294967295 | f >>> 10);
        f = b + (e ^ g & (c ^ e)) + d[1] + 4129170786 & 4294967295;
        b = c + (f <<
            5 & 4294967295 | f >>> 27);
        f = g + (c ^ e & (b ^ c)) + d[6] + 3225465664 & 4294967295;
        g = b + (f << 9 & 4294967295 | f >>> 23);
        f = e + (b ^ c & (g ^ b)) + d[11] + 643717713 & 4294967295;
        e = g + (f << 14 & 4294967295 | f >>> 18);
        f = c + (g ^ b & (e ^ g)) + d[0] + 3921069994 & 4294967295;
        c = e + (f << 20 & 4294967295 | f >>> 12);
        f = b + (e ^ g & (c ^ e)) + d[5] + 3593408605 & 4294967295;
        b = c + (f << 5 & 4294967295 | f >>> 27);
        f = g + (c ^ e & (b ^ c)) + d[10] + 38016083 & 4294967295;
        g = b + (f << 9 & 4294967295 | f >>> 23);
        f = e + (b ^ c & (g ^ b)) + d[15] + 3634488961 & 4294967295;
        e = g + (f << 14 & 4294967295 | f >>> 18);
        f = c + (g ^ b & (e ^ g)) + d[4] + 3889429448 & 4294967295;
        c =
            e + (f << 20 & 4294967295 | f >>> 12);
        f = b + (e ^ g & (c ^ e)) + d[9] + 568446438 & 4294967295;
        b = c + (f << 5 & 4294967295 | f >>> 27);
        f = g + (c ^ e & (b ^ c)) + d[14] + 3275163606 & 4294967295;
        g = b + (f << 9 & 4294967295 | f >>> 23);
        f = e + (b ^ c & (g ^ b)) + d[3] + 4107603335 & 4294967295;
        e = g + (f << 14 & 4294967295 | f >>> 18);
        f = c + (g ^ b & (e ^ g)) + d[8] + 1163531501 & 4294967295;
        c = e + (f << 20 & 4294967295 | f >>> 12);
        f = b + (e ^ g & (c ^ e)) + d[13] + 2850285829 & 4294967295;
        b = c + (f << 5 & 4294967295 | f >>> 27);
        f = g + (c ^ e & (b ^ c)) + d[2] + 4243563512 & 4294967295;
        g = b + (f << 9 & 4294967295 | f >>> 23);
        f = e + (b ^ c & (g ^ b)) + d[7] + 1735328473 & 4294967295;
        e = g + (f << 14 & 4294967295 | f >>> 18);
        f = c + (g ^ b & (e ^ g)) + d[12] + 2368359562 & 4294967295;
        c = e + (f << 20 & 4294967295 | f >>> 12);
        f = b + (c ^ e ^ g) + d[5] + 4294588738 & 4294967295;
        b = c + (f << 4 & 4294967295 | f >>> 28);
        f = g + (b ^ c ^ e) + d[8] + 2272392833 & 4294967295;
        g = b + (f << 11 & 4294967295 | f >>> 21);
        f = e + (g ^ b ^ c) + d[11] + 1839030562 & 4294967295;
        e = g + (f << 16 & 4294967295 | f >>> 16);
        f = c + (e ^ g ^ b) + d[14] + 4259657740 & 4294967295;
        c = e + (f << 23 & 4294967295 | f >>> 9);
        f = b + (c ^ e ^ g) + d[1] + 2763975236 & 4294967295;
        b = c + (f << 4 & 4294967295 | f >>> 28);
        f = g + (b ^ c ^ e) + d[4] + 1272893353 & 4294967295;
        g = b + (f << 11 & 4294967295 |
            f >>> 21);
        f = e + (g ^ b ^ c) + d[7] + 4139469664 & 4294967295;
        e = g + (f << 16 & 4294967295 | f >>> 16);
        f = c + (e ^ g ^ b) + d[10] + 3200236656 & 4294967295;
        c = e + (f << 23 & 4294967295 | f >>> 9);
        f = b + (c ^ e ^ g) + d[13] + 681279174 & 4294967295;
        b = c + (f << 4 & 4294967295 | f >>> 28);
        f = g + (b ^ c ^ e) + d[0] + 3936430074 & 4294967295;
        g = b + (f << 11 & 4294967295 | f >>> 21);
        f = e + (g ^ b ^ c) + d[3] + 3572445317 & 4294967295;
        e = g + (f << 16 & 4294967295 | f >>> 16);
        f = c + (e ^ g ^ b) + d[6] + 76029189 & 4294967295;
        c = e + (f << 23 & 4294967295 | f >>> 9);
        f = b + (c ^ e ^ g) + d[9] + 3654602809 & 4294967295;
        b = c + (f << 4 & 4294967295 | f >>> 28);
        f = g + (b ^ c ^ e) + d[12] +
            3873151461 & 4294967295;
        g = b + (f << 11 & 4294967295 | f >>> 21);
        f = e + (g ^ b ^ c) + d[15] + 530742520 & 4294967295;
        e = g + (f << 16 & 4294967295 | f >>> 16);
        f = c + (e ^ g ^ b) + d[2] + 3299628645 & 4294967295;
        c = e + (f << 23 & 4294967295 | f >>> 9);
        f = b + (e ^ (c | ~g)) + d[0] + 4096336452 & 4294967295;
        b = c + (f << 6 & 4294967295 | f >>> 26);
        f = g + (c ^ (b | ~e)) + d[7] + 1126891415 & 4294967295;
        g = b + (f << 10 & 4294967295 | f >>> 22);
        f = e + (b ^ (g | ~c)) + d[14] + 2878612391 & 4294967295;
        e = g + (f << 15 & 4294967295 | f >>> 17);
        f = c + (g ^ (e | ~b)) + d[5] + 4237533241 & 4294967295;
        c = e + (f << 21 & 4294967295 | f >>> 11);
        f = b + (e ^ (c | ~g)) + d[12] + 1700485571 &
            4294967295;
        b = c + (f << 6 & 4294967295 | f >>> 26);
        f = g + (c ^ (b | ~e)) + d[3] + 2399980690 & 4294967295;
        g = b + (f << 10 & 4294967295 | f >>> 22);
        f = e + (b ^ (g | ~c)) + d[10] + 4293915773 & 4294967295;
        e = g + (f << 15 & 4294967295 | f >>> 17);
        f = c + (g ^ (e | ~b)) + d[1] + 2240044497 & 4294967295;
        c = e + (f << 21 & 4294967295 | f >>> 11);
        f = b + (e ^ (c | ~g)) + d[8] + 1873313359 & 4294967295;
        b = c + (f << 6 & 4294967295 | f >>> 26);
        f = g + (c ^ (b | ~e)) + d[15] + 4264355552 & 4294967295;
        g = b + (f << 10 & 4294967295 | f >>> 22);
        f = e + (b ^ (g | ~c)) + d[6] + 2734768916 & 4294967295;
        e = g + (f << 15 & 4294967295 | f >>> 17);
        f = c + (g ^ (e | ~b)) + d[13] + 1309151649 &
            4294967295;
        c = e + (f << 21 & 4294967295 | f >>> 11);
        f = b + (e ^ (c | ~g)) + d[4] + 4149444226 & 4294967295;
        b = c + (f << 6 & 4294967295 | f >>> 26);
        f = g + (c ^ (b | ~e)) + d[11] + 3174756917 & 4294967295;
        g = b + (f << 10 & 4294967295 | f >>> 22);
        f = e + (b ^ (g | ~c)) + d[2] + 718787259 & 4294967295;
        e = g + (f << 15 & 4294967295 | f >>> 17);
        f = c + (g ^ (e | ~b)) + d[9] + 3951481745 & 4294967295;
        a.chain_[0] = a.chain_[0] + b & 4294967295;
        a.chain_[1] = a.chain_[1] + (e + (f << 21 & 4294967295 | f >>> 11)) & 4294967295;
        a.chain_[2] = a.chain_[2] + e & 4294967295;
        a.chain_[3] = a.chain_[3] + g & 4294967295
    };
    ed.prototype.update = function (a, b) {
        void 0 === b && (b = a.length);
        for (var c = b - this.blockSize, d = this.block_, e = this.blockLength_, g = 0; g < b;) {
            if (0 == e)
                for (; g <= c;) fd(this, a, g), g += this.blockSize;
            if (n(a))
                for (; g < b;) {
                    if (d[e++] = a.charCodeAt(g++), e == this.blockSize) {
                        fd(this, d);
                        e = 0;
                        break
                    }
                } else
                    for (; g < b;)
                        if (d[e++] = a[g++], e == this.blockSize) {
                            fd(this, d);
                            e = 0;
                            break
                        }
        }
        this.blockLength_ = e;
        this.totalLength_ += b
    };
    var Y = function () {
        K.call(this);
        this.callback_ = this.element_ = null;
        this.md5_ = new ed
    };
    r(Y, K);
    var gd = function (a, b, c, d, e) {
        a.unwatch();
        a.element_ = b;
        a.callback_ = e;
        a.listen(b, "keyup", p(a.onChanged_, a, c, d))
    };
    Y.prototype.unwatch = function () {
        this.element_ && this.callback_ && (this.removeAll(), this.callback_ = this.element_ = null)
    };
    Y.prototype.onChanged_ = function (a, b) {
        var c;
        c = (c = this.element_.value) ? c.replace(/[\s\xa0]+/g, "").toLowerCase() : "";
        this.md5_.reset();
        this.md5_.update(c + "." + b);
        c = this.md5_;
        var d = Array((56 > c.blockLength_ ? c.blockSize : 2 * c.blockSize) - c.blockLength_);
        d[0] = 128;
        for (var e = 1; e < d.length - 8; ++e) d[e] = 0;
        for (var g = 8 * c.totalLength_, e = d.length - 8; e < d.length; ++e) d[e] = g & 255, g /= 256;
        c.update(d);
        d = Array(16);
        for (e = g = 0; 4 > e; ++e)
            for (var f = 0; 32 > f; f += 8) d[g++] = c.chain_[e] >>> f & 255;
        cd(d).toLowerCase() == a.toLowerCase() && this.callback_()
    };
    Y.prototype.disposeInternal = function () {
        this.element_ = null;
        Y.superClass_.disposeInternal.call(this)
    };
    var id = function (a, b, c) {
        this.adObject_ = a;
        this.captchaImageUrl_ = b;
        this.opt_successCallback_ = c || null;
        hd(this)
    }, hd = function (a) {
            var b = new N;
            dc(b, "recaptcha_challenge_image", a.captchaImageUrl_);
            dc(b, "recaptcha_ad_image", a.adObject_.imageAdUrl);
            var c = {};
            Nb(b, "load", p(function (a, b) {
                a[b.target.id] = b.target
            }, a, c));
            Nb(b, "complete", p(a.handleImagesLoaded_, a, c));
            b.start()
        };
    id.prototype.handleImagesLoaded_ = function (a) {
        a = new W(a.recaptcha_challenge_image, a.recaptcha_ad_image);
        var b = tb(document, "recaptcha_image");
        yb(b);
        Xc(a, b);
        a.adImage_ && ad(a.adImage_) && (Nc(this.adObject_.delayedImpressionUrl), a = new Y, gd(a, tb(document, "recaptcha_response_field"), this.adObject_.hashedAnswer, this.adObject_.salt, p(function (a, b) {
            a.unwatch();
            Nc(b)
        }, this, a, this.adObject_.engagementUrl)), this.opt_successCallback_ && this.opt_successCallback_("04" + this.adObject_.token))
    };
    var ya = function () {
        var a = l.google_ad;
        return !!(a && a.token && a.imageAdUrl && a.hashedAnswer && a.salt && a.delayedImpressionUrl && a.engagementUrl)
    };
    var X = t;
    q("RecaptchaStr", X);
    var Z = l.RecaptchaOptions;
    q("RecaptchaOptions", Z);
    var jd = {
        tabindex: 0,
        theme: "red",
        callback: null,
        lang: null,
        custom_theme_widget: null,
        custom_translations: null
    };
    q("RecaptchaDefaultOptions", jd);
    var $ = {
        widget: null,
        timer_id: -1,
        style_set: !1,
        theme: null,
        type: "image",
        ajax_verify_cb: null,
        $: function (a) {
            return "string" == typeof a ? document.getElementById(a) : a
        },
        attachEvent: function (a, b, c) {
            a && a.addEventListener ? a.addEventListener(b, c, !1) : a && a.attachEvent && a.attachEvent("on" + b, c)
        },
        create: function (a, b, c) {
            $.destroy();
            b && ($.widget = $.$(b));
            $._init_options(c);
            $._call_challenge(a)
        },
        destroy: function () {
            var a = $.$("recaptcha_challenge_field");
            a && a.parentNode.removeChild(a); - 1 != $.timer_id && clearInterval($.timer_id);
            $.timer_id = -1;
            if (a = $.$("recaptcha_image")) a.innerHTML = "";
            $.widget && ("custom" != $.theme ? $.widget.innerHTML = "" : $.widget.style.display = "none", $.widget = null)
        },
        focus_response_field: function () {
            $.$("recaptcha_response_field").focus()
        },
        get_challenge: function () {
            return "undefined" == typeof RecaptchaState ? null : RecaptchaState.challenge
        },
        get_response: function () {
            var a = $.$("recaptcha_response_field");
            return a ? a.value : null
        },
        ajax_verify: function (a) {
            $.ajax_verify_cb = a;
            a = $.get_challenge() || "";
            var b = $.get_response() || "";
            a = $._get_api_server() + "/ajaxverify?c=" + encodeURIComponent(a) + "&response=" + encodeURIComponent(b);
            $._add_script(a)
        },
        _ajax_verify_callback: function (a) {
            $.ajax_verify_cb(a)
        },
        _get_overridable_url: function (a) {
            var b = window.location.protocol;
            if ("undefined" != typeof _RecaptchaOverrideApiServer) a = _RecaptchaOverrideApiServer;
            else if ("undefined" != typeof RecaptchaState && "string" == typeof RecaptchaState.server && 0 < RecaptchaState.server.length) return RecaptchaState.server.replace(/\/+$/, "");
            return b + "//" + a
        },
        _get_api_server: function () {
            return $._get_overridable_url("www.google.com/recaptcha/api")
        },
        _get_static_url_root: function () {
            return $._get_overridable_url("www.gstatic.com/recaptcha/api")
        },
        _call_challenge: function (a) {
            a = $._get_api_server() + "/challenge?k=" + a + "&ajax=1&cachestop=" + Math.random();
            $.getLang_() && (a += "&lang=" + $.getLang_());
            "undefined" != typeof Z.extra_challenge_params && (a += "&" + Z.extra_challenge_params);
            $._add_script(a)
        },
        _add_script: function (a) {
            var b = document.createElement("script");
            b.type = "text/javascript";
            b.src = a;
            $._get_script_area().appendChild(b)
        },
        _get_script_area: function () {
            var a =
                document.getElementsByTagName("head");
            return a = !a || 1 > a.length ? document.body : a[0]
        },
        _hash_merge: function (a) {
            for (var b = {}, c = 0; c < a.length; c++)
                for (var d in a[c]) b[d] = a[c][d];
            return b
        },
        _init_options: function (a) {
            Z = $._hash_merge([jd, a || {}])
        },
        challenge_callback: function () {
            $._reset_timer();
            X = $._hash_merge([t, ta[$.getLang_()] || {},
                Z.custom_translations || {}
            ]);
            window.addEventListener && window.addEventListener("unload", function () {
                $.destroy()
            }, !1);
            $._is_ie() && window.attachEvent && window.attachEvent("onbeforeunload",
                function () {});
            if (0 < navigator.userAgent.indexOf("KHTML")) {
                var a = document.createElement("iframe");
                a.src = "about:blank";
                a.style.height = "0px";
                a.style.width = "0px";
                a.style.visibility = "hidden";
                a.style.border = "none";
                a.appendChild(document.createTextNode("This frame prevents back/forward cache problems in Safari."));
                document.body.appendChild(a)
            }
            $._finish_widget()
        },
        _add_css: function (a) {
            if (-1 != navigator.appVersion.indexOf("MSIE 5")) document.write('<style type="text/css">' + a + "</style>");
            else {
                var b = document.createElement("style");
                b.type = "text/css";
                b.styleSheet ? b.styleSheet.cssText = a : b.appendChild(document.createTextNode(a));
                $._get_script_area().appendChild(b)
            }
        },
        _set_style: function (a) {
            $.style_set || ($.style_set = !0, $._add_css(a + "\n\n.recaptcha_is_showing_audio .recaptcha_only_if_image,.recaptcha_isnot_showing_audio .recaptcha_only_if_audio,.recaptcha_had_incorrect_sol .recaptcha_only_if_no_incorrect_sol,.recaptcha_nothad_incorrect_sol .recaptcha_only_if_incorrect_sol{display:none !important}"))
        },
        _init_builtin_theme: function () {
            var a =
                $.$,
                b = $._get_static_url_root(),
                c = s.VertCss,
                d = s.VertHtml,
                e = b + "/img/" + $.theme,
                g = "gif",
                b = $.theme;
            "clean" == b && (c = s.CleanCss, d = s.CleanHtml, g = "png");
            c = c.replace(/IMGROOT/g, e);
            $._set_style(c);
            $.widget.innerHTML = '<div id="recaptcha_area">' + d + "</div>";
            c = $.getLang_();
            a("recaptcha_privacy") && null != c && "en" == c.substring(0, 2).toLowerCase() && null != X.privacy_and_terms && 0 < X.privacy_and_terms.length && (c = document.createElement("a"), c.href = "http://www.google.com/intl/en/policies/", c.target = "_blank", c.innerHTML = X.privacy_and_terms,
                a("recaptcha_privacy").appendChild(c));
            c = function (b, c, d, M) {
                var w = a(b);
                w.src = e + "/" + c + "." + g;
                c = X[d];
                w.alt = c;
                b = a(b + "_btn");
                b.title = c;
                $.attachEvent(b, "click", M)
            };
            c("recaptcha_reload", "refresh", "refresh_btn", $.reload);
            c("recaptcha_switch_audio", "audio", "audio_challenge", function () {
                $.switch_type("audio")
            });
            c("recaptcha_switch_img", "text", "visual_challenge", function () {
                $.switch_type("image")
            });
            c("recaptcha_whatsthis", "help", "help_btn", $.showhelp);
            "clean" == b && (a("recaptcha_logo").src = e + "/logo." + g);
            a("recaptcha_table").className =
                "recaptchatable recaptcha_theme_" + $.theme;
            b = function (b, c) {
                var d = a(b);
                d && (RecaptchaState.rtl && "span" == d.tagName.toLowerCase() && (d.dir = "rtl"), d.appendChild(document.createTextNode(X[c])))
            };
            b("recaptcha_instructions_image", "instructions_visual");
            b("recaptcha_instructions_audio", "instructions_audio");
            b("recaptcha_instructions_error", "incorrect_try_again");
            a("recaptcha_instructions_image") || a("recaptcha_instructions_audio") || (b = "audio" == $.type ? X.instructions_audio : X.instructions_visual, b = b.replace(/:$/,
                ""), a("recaptcha_response_field").setAttribute("placeholder", b))
        },
        _finish_widget: function () {
            var a = $.$,
                b = Z,
                c = b.theme;
            c in {
                blackglass: 1,
                clean: 1,
                custom: 1,
                red: 1,
                white: 1
            } || (c = "red");
            $.theme || ($.theme = c);
            "custom" != $.theme ? $._init_builtin_theme() : $._set_style("");
            c = document.createElement("span");
            c.id = "recaptcha_challenge_field_holder";
            c.style.display = "none";
            a("recaptcha_response_field").parentNode.insertBefore(c, a("recaptcha_response_field"));
            a("recaptcha_response_field").setAttribute("autocomplete", "off");
            a("recaptcha_image").style.width = "300px";
            a("recaptcha_image").style.height = "57px";
            $.should_focus = !1;
            $._set_challenge(RecaptchaState.challenge, "image");
            $.updateTabIndexes_();
            $.widget && ($.widget.style.display = "");
            b.callback && b.callback()
        },
        updateTabIndexes_: function () {
            var a = $.$,
                b = Z;
            b.tabindex && (b = b.tabindex, a("recaptcha_response_field").tabIndex = b++, "audio" == $.type && a("recaptcha_audio_play_again") && (a("recaptcha_audio_play_again").tabIndex = b++, a("recaptcha_audio_download"), a("recaptcha_audio_download").tabIndex =
                b++), "custom" != $.theme && (a("recaptcha_reload_btn").tabIndex = b++, a("recaptcha_switch_audio_btn").tabIndex = b++, a("recaptcha_switch_img_btn").tabIndex = b++, a("recaptcha_whatsthis_btn").tabIndex = b, a("recaptcha_privacy").tabIndex = b++))
        },
        switch_type: function (a) {
            $.type = a;
            $.reload("audio" == $.type ? "a" : "v");
            if ("custom" != $.theme) {
                a = $.$;
                var b = "audio" == $.type ? X.instructions_audio : X.instructions_visual,
                    b = b.replace(/:$/, "");
                a("recaptcha_response_field").setAttribute("placeholder", b)
            }
        },
        reload: function (a) {
            var b = Z,
                c = RecaptchaState;
            "undefined" == typeof a && (a = "r");
            c = $._get_api_server() + "/reload?c=" + c.challenge + "&k=" + c.site + "&reason=" + a + "&type=" + $.type;
            $.getLang_() && (c += "&lang=" + $.getLang_());
            "undefined" != typeof b.extra_challenge_params && (c += "&" + b.extra_challenge_params);
            "audio" == $.type && (c = b.audio_beta_12_08 ? c + "&audio_beta_12_08=1" : c + "&new_audio_default=1");
            $.should_focus = "t" != a;
            $._add_script(c)
        },
        finish_reload: function (a, b, c) {
            RecaptchaState.payload_url = c;
            RecaptchaState.is_incorrect = !1;
            $._set_challenge(a, b);
            $.updateTabIndexes_()
        },
        _set_challenge: function (a, b) {
            var c = $.$,
                d = RecaptchaState;
            d.challenge = a;
            $.type = b;
            c("recaptcha_challenge_field_holder").innerHTML = '<input type="hidden" name="recaptcha_challenge_field" id="recaptcha_challenge_field" value="' + d.challenge + '"/>';
            if ("audio" == b) c("recaptcha_image").innerHTML = $.getAudioCaptchaHtml(), $._loop_playback();
            else if ("image" == b) {
                var e = d.payload_url;
                e || (e = $._get_api_server() + "/image?c=" + d.challenge);
                ya() ? (new id(za(), e, function (a) {
                    RecaptchaState.challenge = a;
                    c("recaptcha_challenge_field").value =
                        a
                }), l.google_ad && (l.google_ad = null)) : c("recaptcha_image").innerHTML = '<img id="recaptcha_challenge_image" alt="' + X.image_alt_text + '" height="57" width="300" src="' + e + '" />'
            }
            $._css_toggle("recaptcha_had_incorrect_sol", "recaptcha_nothad_incorrect_sol", d.is_incorrect);
            $._css_toggle("recaptcha_is_showing_audio", "recaptcha_isnot_showing_audio", "audio" == b);
            $._clear_input();
            $.should_focus && $.focus_response_field();
            $._reset_timer()
        },
        _reset_timer: function () {
            clearInterval($.timer_id);
            var a = Math.max(1E3 * (("undefined" != typeof RecaptchaState ? RecaptchaState.timeout : 0) -
                60), 6E4);
            $.timer_id = setInterval(function () {
                $.reload("t")
            }, a);
            return a
        },
        showhelp: function () {
            window.open($._get_help_link(), "recaptcha_popup", "width=460,height=580,location=no,menubar=no,status=no,toolbar=no,scrollbars=yes,resizable=yes")
        },
        _clear_input: function () {
            $.$("recaptcha_response_field").value = ""
        },
        _displayerror: function (a) {
            var b = $.$;
            b("recaptcha_image").innerHTML = "";
            b("recaptcha_image").appendChild(document.createTextNode(a))
        },
        reloaderror: function (a) {
            $._displayerror(a)
        },
        _is_ie: function () {
            return 0 <
                navigator.userAgent.indexOf("MSIE") && !window.opera
        },
        _css_toggle: function (a, b, c) {
            var d = $.widget;
            d || (d = document.body);
            var e = d.className,
                e = e.replace(RegExp("(^|\\s+)" + a + "(\\s+|$)"), " "),
                e = e.replace(RegExp("(^|\\s+)" + b + "(\\s+|$)"), " ");
            d.className = e + (" " + (c ? a : b))
        },
        _get_help_link: function () {
            var a = $._get_api_server().replace(/\/[a-zA-Z0-9]+\/?$/, "/help"),
                a = a + ("?c=" + RecaptchaState.challenge);
            $.getLang_() && (a += "&hl=" + $.getLang_());
            return a
        },
        playAgain: function () {
            $.$("recaptcha_image").innerHTML = $.getAudioCaptchaHtml();
            $._loop_playback()
        },
        _loop_playback: function () {
            var a = $.$("recaptcha_audio_play_again");
            a && $.attachEvent(a, "click", function () {
                $.playAgain();
                return !1
            })
        },
        getAudioCaptchaHtml: function () {
            var a = RecaptchaState.payload_url;
            a || (a = $._get_api_server() + "/audio.mp3?c=" + RecaptchaState.challenge, 0 == a.indexOf("https://") && (a = "http://" + a.substring(8)));
            var b = $._get_static_url_root() + "/img/audiocaptcha.swf?v2",
                b = $._is_ie() ? '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" id="audiocaptcha" width="0" height="0" codebase="https://fpdownload.macromedia.com/get/flashplayer/current/swflash.cab"><param name="movie" value="' +
                    b + '" /><param name="quality" value="high" /><param name="bgcolor" value="#869ca7" /><param name="allowScriptAccess" value="always" /></object><br/>' : '<embed src="' + b + '" quality="high" bgcolor="#869ca7" width="0" height="0" name="audiocaptcha" align="middle" play="true" loop="false" quality="high" allowScriptAccess="always" type="application/x-shockwave-flash" pluginspage="http://www.adobe.com/go/getflashplayer" /></embed>',
                c = "";
            $.checkFlashVer() && (c = "<br/>" + $.getSpan_('<a id="recaptcha_audio_play_again" class="recaptcha_audio_cant_hear_link">' +
                X.play_again + "</a>"));
            c += "<br/>" + $.getSpan_('<a id="recaptcha_audio_download" class="recaptcha_audio_cant_hear_link" target="_blank" href="' + a + '">' + X.cant_hear_this + "</a>");
            return b + c
        },
        getSpan_: function (a) {
            return "<span" + (RecaptchaState && RecaptchaState.rtl ? ' dir="rtl"' : "") + ">" + a + "</span>"
        },
        gethttpwavurl: function () {
            if ("audio" != $.type) return "";
            var a = RecaptchaState.payload_url;
            a || (a = $._get_api_server() + "/image?c=" + RecaptchaState.challenge);
            0 == a.indexOf("https://") && (a = "http://" + a.substring(8));
            return a
        },
        checkFlashVer: function () {
            var a = -1 != navigator.appVersion.indexOf("MSIE"),
                b = -1 != navigator.appVersion.toLowerCase().indexOf("win"),
                c = -1 != navigator.userAgent.indexOf("Opera"),
                d = -1;
            if (null != navigator.plugins && 0 < navigator.plugins.length) {
                if (navigator.plugins["Shockwave Flash 2.0"] || navigator.plugins["Shockwave Flash"]) d = navigator.plugins["Shockwave Flash" + (navigator.plugins["Shockwave Flash 2.0"] ? " 2.0" : "")].description.split(" ")[2].split(".")[0]
            } else if (a && b && !c) try {
                d = (new ActiveXObject("ShockwaveFlash.ShockwaveFlash.7")).GetVariable("$version").split(" ")[1].split(",")[0]
            } catch (e) {}
            return 9 <=
                d
        },
        getLang_: function () {
            return "undefined" != typeof RecaptchaState && RecaptchaState.lang ? RecaptchaState.lang : Z.lang ? Z.lang : null
        }
    };
    q("Recaptcha", $);
    $._init_options(Z);
    Z && "custom" == Z.theme ? Z.custom_theme_widget && ($.widget = $.$(Z.custom_theme_widget)) : (document.write('<div id="recaptcha_widget_div" style="display:none"></div>'), $.widget = $.$("recaptcha_widget_div"));
    //$.challenge_callback();
})()