
function CustomAttributeService() { }
CustomAttributeService._path = '/kra-cnv/dwr';

CustomAttributeService.getLookupReturnsForAjaxCall = function(p0, callback) {
    DWREngine._execute(CustomAttributeService._path, 'CustomAttributeService', 'getLookupReturnsForAjaxCall', p0, callback);
}
