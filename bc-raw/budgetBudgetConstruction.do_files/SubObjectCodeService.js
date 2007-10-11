
function SubObjectCodeService() { }
SubObjectCodeService._path = '/kuali-dev/dwr';

SubObjectCodeService.getByPrimaryId = function(p0, p1, p2, p3, p4, callback) {
    DWREngine._execute(SubObjectCodeService._path, 'SubObjectCodeService', 'getByPrimaryId', p0, p1, p2, p3, p4, callback);
}
