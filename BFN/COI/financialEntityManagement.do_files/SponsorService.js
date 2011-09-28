
function SponsorService() { }
SponsorService._path = '/kc-trunk/dwr';

SponsorService.getSponsorName = function(p0, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'getSponsorName', p0, callback);
}

SponsorService.getSponsor = function(p0, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'getSponsor', p0, callback);
}

SponsorService.getSubSponsorHierarchiesForTreeView = function(p0, p1, p2, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'getSubSponsorHierarchiesForTreeView', p0, p1, p2, callback);
}

SponsorService.getSponsorCodes = function(p0, p1, p2, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'getSponsorCodes', p0, p1, p2, callback);
}

SponsorService.updateSponsorCodes = function(p0, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'updateSponsorCodes', p0, callback);
}

SponsorService.insertSponsor = function(p0, p1, p2, p3, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'insertSponsor', p0, p1, p2, p3, callback);
}

SponsorService.deleteSponsor = function(p0, p1, p2, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'deleteSponsor', p0, p1, p2, callback);
}

SponsorService.updateGroupName = function(p0, p1, p2, p3, p4, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'updateGroupName', p0, p1, p2, p3, p4, callback);
}

SponsorService.changeSponsorSortOrder = function(p0, p1, p2, p3, callback) {
    DWREngine._execute(SponsorService._path, 'SponsorService', 'changeSponsorSortOrder', p0, p1, p2, p3, callback);
}
