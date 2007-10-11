
function ProjectCodeService() { }
ProjectCodeService._path = '/kuali-dev/dwr';

ProjectCodeService.getByPrimaryId = function(p0, callback) {
    DWREngine._execute(ProjectCodeService._path, 'ProjectCodeService', 'getByPrimaryId', p0, callback);
}
