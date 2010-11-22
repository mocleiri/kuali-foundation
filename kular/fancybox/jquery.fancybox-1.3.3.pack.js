/*
 * FancyBox - jQuery Plugin
 * Simple and fancy lightbox alternative
 *
 * Examples and documentation at: http://fancybox.net
 * 
 * Copyright (c) 2008 - 2010 Janis Skarnelis
 * That said, it is hardly a one-person project. Many people have submitted bugs, code, and offered their advice freely. Their support is greatly appreciated.
 * 
 * Version: 1.3.3 (04/11/2010)
 * Requires: jQuery v1.3+
 *
 * Dual licensed under the MIT and GPL licenses:
 *   http://www.opensource.org/licenses/mit-license.php
 *   http://www.gnu.org/licenses/gpl.html
 */

;(function(a){var m,t,u,f,D,j,E,n,z,A,q=0,e={},o=[],p=0,c={},l=[],G=null,v=new Image,J=/\.(jpg|gif|png|bmp|jpeg)(.*)?$/i,W=/[^\.]\.(swf)\s*$/i,K,L=1,y=0,s="",r,i,h=false,B=a.extend(a("<div/>")[0],{prop:0}),M=a.browser.msie&&a.browser.version<7&&!window.XMLHttpRequest,N=function(){t.hide();v.onerror=v.onload=null;G&&G.abort();m.empty()},O=function(){if(false===e.onError(o,q,e)){t.hide();h=false}else{e.titleShow=false;e.width="auto";e.height="auto";m.html('<p id="fancybox-error">The requested content cannot be loaded.<br />Please try again later.</p>');
F()}},I=function(){var b=o[q],d,g,k,C,P,w;N();e=a.extend({},a.fn.fancybox.defaults,typeof a(b).data("fancybox")=="undefined"?e:a(b).data("fancybox"));w=e.onStart(o,q,e);if(w===false)h=false;else{if(typeof w=="object")e=a.extend(e,w);k=e.title||(b.nodeName?a(b).attr("title"):b.title)||"";if(b.nodeName&&!e.orig)e.orig=a(b).children("img:first").length?a(b).children("img:first"):a(b);if(k===""&&e.orig&&e.titleFromAlt)k=e.orig.attr("alt");d=e.href||(b.nodeName?a(b).attr("href"):b.href)||null;if(/^(?:javascript)/i.test(d)||
d=="#")d=null;if(e.type){g=e.type;if(!d)d=e.content}else if(e.content)g="html";else if(d)g=d.match(J)?"image":d.match(W)?"swf":a(b).hasClass("iframe")?"iframe":d.indexOf("#")===0?"inline":"ajax";if(g){if(g=="inline"){b=d.substr(d.indexOf("#"));g=a(b).length>0?"inline":"ajax"}e.type=g;e.href=d;e.title=k;if(e.autoDimensions&&e.type!=="iframe"&&e.type!=="swf"){e.width="auto";e.height="auto"}if(e.modal){e.overlayShow=true;e.hideOnOverlayClick=false;e.hideOnContentClick=false;e.enableEscapeButton=false;
e.showCloseButton=false}e.padding=parseInt(e.padding,10);e.margin=parseInt(e.margin,10);m.css("padding",e.padding+e.margin);a(".fancybox-inline-tmp").unbind("fancybox-cancel").bind("fancybox-change",function(){a(this).replaceWith(j.children())});switch(g){case "html":m.html(e.content);F();break;case "inline":if(a(b).parent().is("#fancybox-content")===true){h=false;break}a('<div class="fancybox-inline-tmp" />').hide().insertBefore(a(b)).bind("fancybox-cleanup",function(){a(this).replaceWith(j.children())}).bind("fancybox-cancel",
function(){a(this).replaceWith(m.children())});a(b).appendTo(m);F();break;case "image":h=false;a.fancybox.showActivity();v=new Image;v.onerror=function(){O()};v.onload=function(){h=true;v.onerror=v.onload=null;e.width=v.width;e.height=v.height;a("<img />").attr({id:"fancybox-img",src:v.src,alt:e.title}).appendTo(m);Q()};v.src=d;break;case "swf":e.scrolling="no";e.autoDimensions=false;C='<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="'+e.width+'" height="'+e.height+'"><param name="movie" value="'+
d+'"></param>';P="";a.each(e.swf,function(x,H){C+='<param name="'+x+'" value="'+H+'"></param>';P+=" "+x+'="'+H+'"'});C+='<embed src="'+d+'" type="application/x-shockwave-flash" width="'+e.width+'" height="'+e.height+'"'+P+"></embed></object>";m.html(C);F();break;case "ajax":h=false;a.fancybox.showActivity();e.ajax.win=e.ajax.success;G=a.ajax(a.extend({},e.ajax,{url:d,data:e.ajax.data||{},error:function(x){x.status>0&&O()},success:function(x,H,R){if((typeof R=="object"?R:G).status==200){if(typeof e.ajax.win==
"function"){w=e.ajax.win(d,x,H,R);if(w===false){t.hide();return}else if(typeof w=="string"||typeof w=="object")x=w}m.html(x);F()}}}));break;case "iframe":e.autoDimensions=false;Q()}}else O()}},F=function(){m.wrapInner('<div style="width:'+(e.width=="auto"?"auto":e.width+"px")+";height:"+(e.height=="auto"?"auto":e.height+"px")+";overflow: "+(e.scrolling=="auto"?"auto":e.scrolling=="yes"?"scroll":"hidden")+'"></div>');e.width=m.width();e.height=m.height();Q()},Q=function(){var b,d;t.hide();if(f.is(":visible")&&
false===c.onCleanup(l,p,c)){a.event.trigger("fancybox-cancel");h=false}else{h=true;a(j.add(u)).unbind();a(window).unbind("resize.fb scroll.fb");a(document).unbind("keydown.fb");f.is(":visible")&&c.titlePosition!=="outside"&&f.css("height",f.height());l=o;p=q;c=e;if(c.overlayShow){u.css({"background-color":c.overlayColor,opacity:c.overlayOpacity,cursor:c.hideOnOverlayClick?"pointer":"auto",height:a(document).height()});if(!u.is(":visible")){M&&a("select:not(#fancybox-tmp select)").filter(function(){return this.style.visibility!==
"hidden"}).css({visibility:"hidden"}).one("fancybox-cleanup",function(){this.style.visibility="inherit"});u.show()}}else u.hide();i=X();s=c.title||"";y=0;n.empty().removeAttr("style").removeClass();if(c.titleShow!==false){if(a.isFunction(c.titleFormat))b=c.titleFormat(s,l,p,c);else b=s&&s.length?c.titlePosition=="float"?'<table id="fancybox-title-float-wrap" cellpadding="0" cellspacing="0"><tr><td id="fancybox-title-float-left"></td><td id="fancybox-title-float-main">'+s+'</td><td id="fancybox-title-float-right"></td></tr></table>':
'<div id="fancybox-title-'+c.titlePosition+'">'+s+"</div>":false;s=b;if(!(!s||s==="")){n.addClass("fancybox-title-"+c.titlePosition).html(s).appendTo("body").show();switch(c.titlePosition){case "inside":n.css({width:i.width-c.padding*2,marginLeft:c.padding,marginRight:c.padding});y=n.outerHeight(true);n.appendTo(D);i.height+=y;break;case "over":n.css({marginLeft:c.padding,width:i.width-c.padding*2,bottom:c.padding}).appendTo(D);break;case "float":n.css("left",parseInt((n.width()-i.width-40)/2,10)*
-1).appendTo(f);break;default:n.css({width:i.width-c.padding*2,paddingLeft:c.padding,paddingRight:c.padding}).appendTo(f)}}}n.hide();if(f.is(":visible")){a(E.add(z).add(A)).hide();b=f.position();r={top:b.top,left:b.left,width:f.width(),height:f.height()};d=r.width==i.width&&r.height==i.height;j.fadeTo(c.changeFade,0.3,function(){var g=function(){j.html(m.contents()).fadeTo(c.changeFade,1,S)};a.event.trigger("fancybox-change");j.empty().removeAttr("filter").css({"border-width":c.padding,width:i.width-
c.padding*2,height:e.autoDimensions?"auto":i.height-y-c.padding*2});if(d)g();else{B.prop=0;a(B).animate({prop:1},{duration:c.changeSpeed,easing:c.easingChange,step:T,complete:g})}})}else{f.removeAttr("style");j.css("border-width",c.padding);if(c.transitionIn=="elastic"){r=V();j.html(m.contents());f.show();if(c.opacity)i.opacity=0;B.prop=0;a(B).animate({prop:1},{duration:c.speedIn,easing:c.easingIn,step:T,complete:S})}else{c.titlePosition=="inside"&&y>0&&n.show();j.css({width:i.width-c.padding*2,height:e.autoDimensions?
"auto":i.height-y-c.padding*2}).html(m.contents());f.css(i).fadeIn(c.transitionIn=="none"?0:c.speedIn,S)}}}},Y=function(){if(c.enableEscapeButton||c.enableKeyboardNav)a(document).bind("keydown.fb",function(b){if(b.keyCode==27&&c.enableEscapeButton){b.preventDefault();a.fancybox.close()}else if((b.keyCode==37||b.keyCode==39)&&c.enableKeyboardNav&&b.target.tagName!=="INPUT"&&b.target.tagName!=="TEXTAREA"&&b.target.tagName!=="SELECT"){b.preventDefault();a.fancybox[b.keyCode==37?"prev":"next"]()}});if(c.showNavArrows){if(c.cyclic&&
l.length>1||p!==0)z.show();if(c.cyclic&&l.length>1||p!=l.length-1)A.show()}else{z.hide();A.hide()}},S=function(){if(!a.support.opacity){j.get(0).style.removeAttribute("filter");f.get(0).style.removeAttribute("filter")}if(e.autoDimensions){f.css("height","auto");j.css("height","auto")}s&&s.length&&n.show();c.showCloseButton&&E.show();Y();c.hideOnContentClick&&j.bind("click",a.fancybox.close);c.hideOnOverlayClick&&u.bind("click",a.fancybox.close);a(window).bind("resize.fb",a.fancybox.resize);c.centerOnScroll&&
a(window).bind("scroll.fb",a.fancybox.center);if(c.type=="iframe")a('<iframe id="fancybox-frame" name="fancybox-frame'+(new Date).getTime()+'" frameborder="0" hspace="0" '+(a.browser.msie?'allowtransparency="true""':"")+' scrolling="'+e.scrolling+'" src="'+c.href+'"></iframe>').appendTo(j);f.show();h=false;a.fancybox.center();c.onComplete(l,p,c);var b,d;if(l.length-1>p){b=l[p+1].href;if(typeof b!=="undefined"&&b.match(J)){d=new Image;d.src=b}}if(p>0){b=l[p-1].href;if(typeof b!=="undefined"&&b.match(J)){d=
new Image;d.src=b}}},T=function(b){var d={width:parseInt(r.width+(i.width-r.width)*b,10),height:parseInt(r.height+(i.height-r.height)*b,10),top:parseInt(r.top+(i.top-r.top)*b,10),left:parseInt(r.left+(i.left-r.left)*b,10)};if(typeof i.opacity!=="undefined")d.opacity=b<0.5?0.5:b;f.css(d);j.css({width:d.width-c.padding*2,height:d.height-y*b-c.padding*2})},U=function(){return[a(window).width()-c.margin*2,a(window).height()-c.margin*2,a(document).scrollLeft()+c.margin,a(document).scrollTop()+c.margin]},
X=function(){var b=U(),d={},g=c.autoScale,k=c.padding*2;d.width=c.width.toString().indexOf("%")>-1?parseInt(b[0]*parseFloat(c.width)/100,10):c.width+k;d.height=c.height.toString().indexOf("%")>-1?parseInt(b[1]*parseFloat(c.height)/100,10):c.height+k;if(g&&(d.width>b[0]||d.height>b[1]))if(e.type=="image"||e.type=="swf"){g=c.width/c.height;if(d.width>b[0]){d.width=b[0];d.height=parseInt((d.width-k)/g+k,10)}if(d.height>b[1]){d.height=b[1];d.width=parseInt((d.height-k)*g+k,10)}}else{d.width=Math.min(d.width,
b[0]);d.height=Math.min(d.height,b[1])}d.top=parseInt(Math.max(b[3]-20,b[3]+(b[1]-d.height-40)*0.5),10);d.left=parseInt(Math.max(b[2]-20,b[2]+(b[0]-d.width-40)*0.5),10);return d},V=function(){var b=e.orig?a(e.orig):false,d={};if(b&&b.length){d=b.offset();d.top+=parseInt(b.css("paddingTop"),10)||0;d.left+=parseInt(b.css("paddingLeft"),10)||0;d.top+=parseInt(b.css("border-top-width"),10)||0;d.left+=parseInt(b.css("border-left-width"),10)||0;d.width=b.width();d.height=b.height();d={width:d.width+c.padding*
2,height:d.height+c.padding*2,top:d.top-c.padding-20,left:d.left-c.padding-20}}else{b=U();d={width:c.padding*2,height:c.padding*2,top:parseInt(b[3]+b[1]*0.5,10),left:parseInt(b[2]+b[0]*0.5,10)}}return d},Z=function(){if(t.is(":visible")){a("div",t).css("top",L*-40+"px");L=(L+1)%12}else clearInterval(K)};a.fn.fancybox=function(b){if(!a(this).length)return this;a(this).data("fancybox",a.extend({},b,a.metadata?a(this).metadata():{})).unbind("click.fb").bind("click.fb",function(d){d.preventDefault();
if(!h){h=true;a(this).blur();o=[];q=0;d=a(this).attr("rel")||"";if(!d||d==""||d==="nofollow")o.push(this);else{o=a("a[rel="+d+"], area[rel="+d+"]");q=o.index(this)}I()}});return this};a.fancybox=function(b,d){var g;if(!h){h=true;g=typeof d!=="undefined"?d:{};o=[];q=parseInt(g.index,10)||0;if(a.isArray(b)){for(var k=0,C=b.length;k<C;k++)if(typeof b[k]=="object")a(b[k]).data("fancybox",a.extend({},g,b[k]));else b[k]=a({}).data("fancybox",a.extend({content:b[k]},g));o=jQuery.merge(o,b)}else{if(typeof b==
"object")a(b).data("fancybox",a.extend({},g,b));else b=a({}).data("fancybox",a.extend({content:b},g));o.push(b)}if(q>o.length||q<0)q=0;I()}};a.fancybox.showActivity=function(){clearInterval(K);t.show();K=setInterval(Z,66)};a.fancybox.hideActivity=function(){t.hide()};a.fancybox.next=function(){return a.fancybox.pos(p+1)};a.fancybox.prev=function(){return a.fancybox.pos(p-1)};a.fancybox.pos=function(b){if(!h){b=parseInt(b);o=l;if(b>-1&&b<l.length){q=b;I()}else if(c.cyclic&&l.length>1){q=b>=l.length?
0:l.length-1;I()}}};a.fancybox.cancel=function(){if(!h){h=true;a.event.trigger("fancybox-cancel");N();e.onCancel(o,q,e);h=false}};a.fancybox.close=function(){function b(){u.fadeOut("fast");n.empty().hide();f.hide();a.event.trigger("fancybox-cleanup");j.empty();c.onClosed(l,p,c);l=e=[];p=q=0;c=e={};h=false}if(!(h||f.is(":hidden"))){h=true;if(c&&false===c.onCleanup(l,p,c))h=false;else{N();a(E.add(z).add(A)).hide();a(j.add(u)).unbind();a(window).unbind("resize.fb scroll.fb");a(document).unbind("keydown.fb");
j.find("iframe").attr("src",M&&/^https/i.test(window.location.href||"")?"javascript:void(false)":"about:blank");c.titlePosition!=="inside"&&n.empty();f.stop();if(c.transitionOut=="elastic"){r=V();var d=f.position();i={top:d.top,left:d.left,width:f.width(),height:f.height()};if(c.opacity)i.opacity=1;n.empty().hide();B.prop=1;a(B).animate({prop:0},{duration:c.speedOut,easing:c.easingOut,step:T,complete:b})}else f.fadeOut(c.transitionOut=="none"?0:c.speedOut,b)}}};a.fancybox.resize=function(){u.is(":visible")&&
u.css("height",a(document).height());a.fancybox.center(true)};a.fancybox.center=function(b){var d,g;if(!h){g=b===true?1:0;d=U();!g&&(f.width()>d[0]||f.height()>d[1])||f.stop().animate({top:parseInt(Math.max(d[3]-20,d[3]+(d[1]-j.height()-40)*0.5-c.padding)),left:parseInt(Math.max(d[2]-20,d[2]+(d[0]-j.width()-40)*0.5-c.padding))},typeof b=="number"?b:200)}};a.fancybox.init=function(){if(!a("#fancybox-wrap").length){a("body").append(m=a('<div id="fancybox-tmp"></div>'),t=a('<div id="fancybox-loading"><div></div></div>'),
u=a('<div id="fancybox-overlay"></div>'),f=a('<div id="fancybox-wrap"></div>'));D=a('<div id="fancybox-outer"></div>').append('<div class="fancybox-bg" id="fancybox-bg-n"></div><div class="fancybox-bg" id="fancybox-bg-ne"></div><div class="fancybox-bg" id="fancybox-bg-e"></div><div class="fancybox-bg" id="fancybox-bg-se"></div><div class="fancybox-bg" id="fancybox-bg-s"></div><div class="fancybox-bg" id="fancybox-bg-sw"></div><div class="fancybox-bg" id="fancybox-bg-w"></div><div class="fancybox-bg" id="fancybox-bg-nw"></div>').appendTo(f);
D.append(j=a('<div id="fancybox-content"></div>'),E=a('<a id="fancybox-close"></a>'),n=a('<div id="fancybox-title"></div>'),z=a('<a href="javascript:;" id="fancybox-left"><span class="fancy-ico" id="fancybox-left-ico"></span></a>'),A=a('<a href="javascript:;" id="fancybox-right"><span class="fancy-ico" id="fancybox-right-ico"></span></a>'));E.click(a.fancybox.close);t.click(a.fancybox.cancel);z.click(function(b){b.preventDefault();a.fancybox.prev()});A.click(function(b){b.preventDefault();a.fancybox.next()});
a.fn.mousewheel&&f.bind("mousewheel.fb",function(b,d){if(h||c.type=="image")b.preventDefault();a.fancybox[d>0?"prev":"next"]()});a.support.opacity||f.addClass("fancybox-ie");if(M){t.addClass("fancybox-ie6");f.addClass("fancybox-ie6");a('<iframe id="fancybox-hide-sel-frame" src="'+(/^https/i.test(window.location.href||"")?"javascript:void(false)":"about:blank")+'" scrolling="no" border="0" frameborder="0" tabindex="-1"></iframe>').prependTo(D)}}};a.fn.fancybox.defaults={padding:10,margin:40,opacity:false,
modal:false,cyclic:false,scrolling:"auto",width:560,height:340,autoScale:true,autoDimensions:true,centerOnScroll:false,ajax:{},swf:{wmode:"transparent"},hideOnOverlayClick:true,hideOnContentClick:false,overlayShow:true,overlayOpacity:0.7,overlayColor:"#777",titleShow:true,titlePosition:"float",titleFormat:null,titleFromAlt:false,transitionIn:"fade",transitionOut:"fade",speedIn:300,speedOut:300,changeSpeed:300,changeFade:"fast",easingIn:"swing",easingOut:"swing",showCloseButton:true,showNavArrows:true,
enableEscapeButton:true,enableKeyboardNav:true,onStart:function(){},onCancel:function(){},onComplete:function(){},onCleanup:function(){},onClosed:function(){},onError:function(){}};a(document).ready(function(){a.fancybox.init()})})(jQuery);