
function ProposalDevelopmentService() { }
ProposalDevelopmentService._path = '/kc-trunk/dwr';

ProposalDevelopmentService.populateProposalEditableFieldMetaDataForAjaxCall = function(p0, p1, callback) {
    DWREngine._execute(ProposalDevelopmentService._path, 'ProposalDevelopmentService', 'populateProposalEditableFieldMetaDataForAjaxCall', p0, p1, callback);
}

ProposalDevelopmentService.isGrantsGovEnabledOnSponsorChange = function(p0, p1, callback) {
    DWREngine._execute(ProposalDevelopmentService._path, 'ProposalDevelopmentService', 'isGrantsGovEnabledOnSponsorChange', p0, p1, callback);
}
