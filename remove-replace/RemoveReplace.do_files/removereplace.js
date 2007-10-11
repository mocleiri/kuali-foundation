// RemoveReplaceEntry.jsp

function selectAllRuleCheckboxes(num) {
	var masterCheckbox = document.getElementById('masterRuleCheckbox');
	if (isCheckbox(masterCheckbox)) {
		for (var i = 0; i < num; i++) {
			var checkbox = document.getElementById('rules[' + i + '].selected');
			if(isCheckbox(checkbox)) {
				checkbox.checked = masterCheckbox.checked;
			}
		}
	}
}

function selectAllWorkgroupCheckboxes(num) {
	var masterCheckbox = document.getElementById('masterWorkgroupCheckbox');
	if (isCheckbox(masterCheckbox)) {
		for (var i = 0; i < num; i++) {
			var checkbox = document.getElementById('workgroups[' + i + '].selected');
			if(isCheckbox(checkbox)) {
				checkbox.checked = masterCheckbox.checked;
			}
		}
	}
}

function isCheckbox(element) {
	return element && element.type && element.type == 'checkbox';
}