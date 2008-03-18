
function ObjectTypeService() { }
ObjectTypeService._path = '/kuali-cnv/dwr';

ObjectTypeService.getByPrimaryKey = function(p0, callback) {
    DWREngine._execute(ObjectTypeService._path, 'ObjectTypeService', 'getByPrimaryKey', p0, callback);
}
