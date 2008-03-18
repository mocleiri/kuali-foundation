
function AccountService() { }
AccountService._path = '/kuali-cnv/dwr';

AccountService.getByPrimaryIdWithCaching = function(p0, p1, callback) {
    DWREngine._execute(AccountService._path, 'AccountService', 'getByPrimaryIdWithCaching', p0, p1, callback);
}
