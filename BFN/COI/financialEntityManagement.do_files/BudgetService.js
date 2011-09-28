
function BudgetService() { }
BudgetService._path = '/kc-trunk/dwr';

BudgetService.getApplicableCostElementsForAjaxCall = function(p0, p1, p2, callback) {
    DWREngine._execute(BudgetService._path, 'BudgetService', 'getApplicableCostElementsForAjaxCall', p0, p1, p2, callback);
}
