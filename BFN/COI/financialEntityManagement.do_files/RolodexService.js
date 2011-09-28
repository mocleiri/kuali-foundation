
function RolodexService() { }
RolodexService._path = '/kc-trunk/dwr';

RolodexService.getRolodex = function(p0, callback) {
    DWREngine._execute(RolodexService._path, 'RolodexService', 'getRolodex', p0, callback);
}
