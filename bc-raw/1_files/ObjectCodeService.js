
function ObjectCodeService() { }
ObjectCodeService._path = '/kuali-dev/dwr';

ObjectCodeService.getByPrimaryId = function(p0, p1, p2, callback) {
    DWREngine._execute(ObjectCodeService._path, 'ObjectCodeService', 'getByPrimaryId', p0, p1, p2, callback);
}

ObjectCodeService.getByPrimaryIdForCurrentYear = function(p0, p1, callback) {
    DWREngine._execute(ObjectCodeService._path, 'ObjectCodeService', 'getByPrimaryIdForCurrentYear', p0, p1, callback);
}

ObjectCodeService.getObjectCodeNamesByCharts = function(p0, p1, p2, callback) {
    DWREngine._execute(ObjectCodeService._path, 'ObjectCodeService', 'getObjectCodeNamesByCharts', p0, p1, p2, callback);
}
