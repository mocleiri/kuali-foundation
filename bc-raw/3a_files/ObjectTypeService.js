
function ObjectTypeService() { }
ObjectTypeService._path = '/kuali-dev/dwr';

ObjectTypeService.getByPrimaryKey = function(p0, callback) {
    DWREngine._execute(ObjectTypeService._path, 'ObjectTypeService', 'getByPrimaryKey', p0, callback);
}
