
function DocumentTypeService() { }
DocumentTypeService._path = '/kuali-cnv/dwr';

DocumentTypeService.getDocumentTypeByCode = function(p0, callback) {
    DWREngine._execute(DocumentTypeService._path, 'DocumentTypeService', 'getDocumentTypeByCode', p0, callback);
}
