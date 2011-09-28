
function S2SService() { }
S2SService._path = '/kc-trunk/dwr';

S2SService.getStatusDetails = function(p0, p1, callback) {
    DWREngine._execute(S2SService._path, 'S2SService', 'getStatusDetails', p0, p1, callback);
}
