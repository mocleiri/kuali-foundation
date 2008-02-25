
function UserService() { }
UserService._path = '/kra-reg/dwr';

UserService.getUniversalUserByAuthenticationUserId = function(p0, callback) {
    DWREngine._execute(UserService._path, 'UserService', 'getUniversalUserByAuthenticationUserId', p0, callback);
}
