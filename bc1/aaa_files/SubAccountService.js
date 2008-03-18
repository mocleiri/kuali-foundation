
function SubAccountService() { }
SubAccountService._path = '/kuali-cnv/dwr';

SubAccountService.getByPrimaryId = function(p0, p1, p2, callback) {
    DWREngine._execute(SubAccountService._path, 'SubAccountService', 'getByPrimaryId', p0, p1, p2, callback);
}
