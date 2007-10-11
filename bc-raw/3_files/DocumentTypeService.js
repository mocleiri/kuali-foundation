
function DocumentTypeService() { }
DocumentTypeService._path = '/kuali-dev/dwr';

DocumentTypeService.getDocumentTypeByCode = function(p0, callback) {
    DWREngine._execute(DocumentTypeService._path, 'DocumentTypeService', 'getDocumentTypeByCode', p0, callback);
}
