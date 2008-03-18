
function ChartService() { }
ChartService._path = '/kuali-cnv/dwr';

ChartService.getByPrimaryId = function(p0, callback) {
    DWREngine._execute(ChartService._path, 'ChartService', 'getByPrimaryId', p0, callback);
}
