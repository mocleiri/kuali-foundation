
function AwardTemplateReportTermService() { }
AwardTemplateReportTermService._path = '/kc-trunk/dwr';

AwardTemplateReportTermService.getReportTypeForAjaxCall = function(p0, callback) {
    DWREngine._execute(AwardTemplateReportTermService._path, 'AwardTemplateReportTermService', 'getReportTypeForAjaxCall', p0, callback);
}

AwardTemplateReportTermService.getFrequencyForAjaxCall = function(p0, p1, callback) {
    DWREngine._execute(AwardTemplateReportTermService._path, 'AwardTemplateReportTermService', 'getFrequencyForAjaxCall', p0, p1, callback);
}

AwardTemplateReportTermService.getFrequencyBaseForAjaxCall = function(p0, callback) {
    DWREngine._execute(AwardTemplateReportTermService._path, 'AwardTemplateReportTermService', 'getFrequencyBaseForAjaxCall', p0, callback);
}
