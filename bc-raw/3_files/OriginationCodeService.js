
function OriginationCodeService() { }
OriginationCodeService._path = '/kuali-dev/dwr';

OriginationCodeService.getByPrimaryKey = function(p0, callback) {
    DWREngine._execute(OriginationCodeService._path, 'OriginationCodeService', 'getByPrimaryKey', p0, callback);
}
