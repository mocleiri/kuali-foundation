/*
 * uses functions defined in objectInfoUtils.js
 */


function loadBankInfo(kualiForm, bankCodeName, target) {
	var bankCode = cleanupElementValue( kualiForm, bankCodeName );

	if (bankCode=='') {
		clearTarget(kualiForm, target);
	} else {
		loadKualiObjectInfo2(kualiForm, 'bank', bankCode, null, null, null, null, target);
	}
}


function loadBankAccountInfo(kualiForm, bankCodeName, bankAccountNumberName, target) {
	var bankCode = cleanupElementValue( kualiForm, bankCodeName );
	var bankAccountNumber = cleanupElementValue( kualiForm, bankAccountNumberName );

	if (bankAccountNumber=='') {
		clearTarget(target);
	} else if (bankCode=='') {
		setTargetValue(target, 'bank is empty');
	} else {
		loadKualiObjectInfo2(kualiForm, 'bankAccount', bankCode, bankAccountNumber, null, null, null, target);
	}
}
