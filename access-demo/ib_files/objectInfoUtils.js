String.prototype.trim = function() { return this.replace(/^\s+|\s+$/, ''); };


/*
 * Given an form and an element name, returns the uppercased, trimmed value of that element
 */
function cleanupElementValue( kualiForm, name ) {
    var element = kualiForm.elements[ name ];

    if ( typeof element == 'undefined' ) {
        alert( 'undefined element "' + name + '"' );
    }

    element.value = element.value.toUpperCase().trim();

    return element.value;
}


/*
 * Uses XMLHttpRequest to submit a request to the loadObjectInfo action, and replaces the current
 * value of the given target with the value returned.
 */
function loadKualiObjectInfo2(kualiForm, methodToCall, param1, param2, param3, param4, param5, target) {
    var req = new XMLHttpRequest();
    if (req) {
        req.onreadystatechange = function() {
            if (req.readyState == 4 && req.status == 200) {
                setTargetValue(kualiForm, target, req.responseText);
            }
        };

        req.open('GET', 'loadObjectInfo.do?methodToCall='+methodToCall+'&param1='+param1+'&param2='+param2+'&param3='+param3+'&param4='+param4+'&param5='+param5);
        req.send(null);
    }
}


/*
 * Clears the value of the given target.
 */
function clearTarget(kualiForm, targetBase) {
    setTargetValue(kualiForm, targetBase, '');
}


/*
 * Sets the value contained by the named div to the given value, or to a non-breaking space if the given value is empty.
 */
function setTargetValue(kualiForm, targetBase, value) {
    var containerHidden = kualiForm.elements[targetBase];
    var containerName = targetBase + '.div';

    var containerDiv = document.getElementById( containerName );

    if (containerDiv) {
        if (value == '') {
            containerDiv.innerHTML = '&nbsp;';
        } else {
            containerDiv.innerHTML = value;
        }
    }
    if (containerHidden) {
        containerHidden.value = value;
    }
}

/*
    json.js
    2006-04-28

Copyright (c) 2002 JSON.org

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 

    This file adds these methods to JavaScript:

        object.toJSONString()

            This method produces a JSON text from an object. The
            object must not contain any cyclical references.

        array.toJSONString()

            This method produces a JSON text from an array. The
            array must not contain any cyclical references.

        string.parseJSON()

            This method parses a JSON text to produce an object or
            array. It will return false if there is an error.
*/
(function () {
    var m = {
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        },
        s = {
            array: function (x) {
                var a = ['['], b, f, i, l = x.length, v;
                for (i = 0; i < l; i += 1) {
                    v = x[i];
                    f = s[typeof v];
                    if (f) {
                        v = f(v);
                        if (typeof v == 'string') {
                            if (b) {
                                a[a.length] = ',';
                            }
                            a[a.length] = v;
                            b = true;
                        }
                    }
                }
                a[a.length] = ']';
                return a.join('');
            },
            'boolean': function (x) {
                return String(x);
            },
            'null': function (x) {
                return "null";
            },
            number: function (x) {
                return isFinite(x) ? String(x) : 'null';
            },
            object: function (x) {
                if (x) {
                    if (x instanceof Array) {
                        return s.array(x);
                    }
                    var a = ['{'], b, f, i, v;
                    for (i in x) {
                        v = x[i];
                        f = s[typeof v];
                        if (f) {
                            v = f(v);
                            if (typeof v == 'string') {
                                if (b) {
                                    a[a.length] = ',';
                                }
                                a.push(s.string(i), ':', v);
                                b = true;
                            }
                        }
                    }
                    a[a.length] = '}';
                    return a.join('');
                }
                return 'null';
            },
            string: function (x) {
                if (/["\\\x00-\x1f]/.test(x)) {
                    x = x.replace(/([\x00-\x1f\\"])/g, function(a, b) {
                        var c = m[b];
                        if (c) {
                            return c;
                        }
                        c = b.charCodeAt();
                        return '\\u00' +
                            Math.floor(c / 16).toString(16) +
                            (c % 16).toString(16);
                    });
                }
                return '"' + x + '"';
            }
        };

    Object.prototype.toJSONString = function () {
        return s.object(this);
    };

    Array.prototype.toJSONString = function () {
        return s.array(this);
    };
})();

String.prototype.parseJSON = function () {
    try {
        return !(/[^,:{}\[\]0-9.\-+Eaeflnr-u \n\r\t]/.test(
                this.replace(/"(\\.|[^"\\])*"/g, ''))) &&
            eval('(' + this + ')');
    } catch (e) {
        return false;
    }
};
