
function ArgValueLookupService() { }
ArgValueLookupService._path = '/kc-trunk/dwr';

ArgValueLookupService.getArgumentNames = function(callback) {
    DWREngine._execute(ArgValueLookupService._path, 'ArgValueLookupService', 'getArgumentNames', callback);
}
