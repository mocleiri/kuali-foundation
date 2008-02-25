
function PostalZipCodeService() { }
PostalZipCodeService._path = '/kuali-cnv/dwr';

PostalZipCodeService.getByPrimaryId = function(p0, callback) {
    DWREngine._execute(PostalZipCodeService._path, 'PostalZipCodeService', 'getByPrimaryId', p0, callback);
}
