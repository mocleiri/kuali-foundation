function setDate(id, month, day, year) {
    if (id == "calendar_0") {
        document.forms['fm'].calendar_event_sd_month.selectedIndex = getIndex(document.forms['fm'].calendar_event_sd_month, month);
        document.forms['fm'].calendar_event_sd_day.selectedIndex = getIndex(document.forms['fm'].calendar_event_sd_day, day);
        document.forms['fm'].calendar_event_sd_year.selectedIndex = getIndex(document.forms['fm'].calendar_event_sd_year, year);
    }
    else if (id == "calendar_1") {
        document.forms['fm'].calendar_event_ed_month.selectedIndex = getIndex(document.forms['fm'].calendar_event_ed_month, month);
        document.forms['fm'].calendar_event_ed_day.selectedIndex = getIndex(document.forms['fm'].calendar_event_ed_day, day);
        document.forms['fm'].calendar_event_ed_year.selectedIndex = getIndex(document.forms['fm'].calendar_event_ed_year, year);
    }
}

function getSelectedRadioButton() {
    var radioGroup = document.forms['fm'].calendar_event_rec_type;
    for (var i = 0; i < radioGroup.length; i++) {
        if (radioGroup[i].checked) {
            return i;
        }
    }
    return 0;
}

function showTab(id) {
    if (document.getElementById) {
        document.getElementById("whole_page").style.display = "block";
        document.getElementById("info_tab").style.display = "none";
        document.getElementById("repeat_tab").style.display = "none";
        document.getElementById("reminders_tab").style.display = "none";
        document.getElementById("invite_tab").style.display = "none";
        document.getElementById(id).style.display = "block";
        
        if (id == "info_tab") {
			showAllDay();
		} else {
        	document.getElementById("startTime").style.display = "none";
	    	document.getElementById("startTimeBoxes").style.display = "none";
	    	document.getElementById("endTime").style.display = "none";
	    	document.getElementById("endTimeBoxes").style.display = "none";
		}
        if (id == "repeat_tab") {
            showTable(repeat_type);
        } else {
            document.getElementById("calendar_never_table").style.display = "none";
            document.getElementById("calendar_daily_table").style.display = "none";
            document.getElementById("calendar_weekly_table").style.display = "none";
            document.getElementById("calendar_monthly_table").style.display = "none";
            document.getElementById("calendar_yearly_table").style.display = "none";
        }
    } else {
        document.all["whole_page"].style.display = "block";
        document.all["info_tab"].style.display = "none";
        document.all["repeat_tab"].style.display = "none";
        document.all["reminders_tab"].style.display = "none";
        document.all["invite_tab"].style.display = "none";
        document.all[id].style.display = "block";
        if (id == "info_tab") {
			showAllDay();
		} else {
	    	document.all["startTime"].style.display = "none";
	    	document.all["startTimeBoxes"].style.display = "none";
	    	document.all["endTime"].style.display = "none";
	    	document.all["endTimeBoxes"].style.display = "none";
		}
        if (id == "repeat_tab") {
            showTable(repeat_type);
        } else {
            document.all["calendar_never_table"].style.display = "none";
            document.all["calendar_daily_table"].style.display = "none";
            document.all["calendar_weekly_table"].style.display = "none";
            document.all["calendar_monthly_table"].style.display = "none";
            document.all["calendar_yearly_table"].style.display = "none";
        }
    }
}
function showTable(id) {
    if (document.getElementById) {
        document.getElementById("calendar_never_table").style.display = "none";
        document.getElementById("calendar_daily_table").style.display = "none";
        document.getElementById("calendar_weekly_table").style.display = "none";
        document.getElementById("calendar_monthly_table").style.display = "none";
        document.getElementById("calendar_yearly_table").style.display = "none";
        document.getElementById(id).style.display = "block";
    } else {
        document.all["calendar_never_table"].style.display = "none";
        document.all["calendar_daily_table"].style.display = "none";
        document.all["calendar_weekly_table"].style.display = "none";
        document.all["calendar_monthly_table"].style.display = "none";
        document.all["calendar_yearly_table"].style.display = "none";
        document.all[id].style.display = "block";
    }
}

function showAllDay() {
    if (document.getElementById) {
    	if(document.forms['fm'].elements['webEvent.allDay'].checked){
	    	document.getElementById("startTime").style.display = "none";
	    	document.getElementById("startTimeBoxes").style.display = "none";
	    	document.getElementById("endTime").style.display = "none";
	    	document.getElementById("endTimeBoxes").style.display = "none";
    	} else {
	    	document.getElementById("startTime").style.display = "block";
	    	document.getElementById("startTimeBoxes").style.display = "block";
	    	document.getElementById("endTime").style.display = "block";
	    	document.getElementById("endTimeBoxes").style.display = "block";
    	}
    } else {
    	if(document.forms['fm'].elements['webEvent.allDay'].checked){
	    	document.all["startTime"].style.display = "none";
	    	document.all["startTimeBoxes"].style.display = "none";
	    	document.all["endTime"].style.display = "none";
	    	document.all["endTimeBoxes"].style.display = "none";
    	} else {
	    	document.all["startTime"].style.display = "block";
	    	document.all["startTimeBoxes"].style.display = "block";
	    	document.all["endTime"].style.display = "block";
	    	document.all["endTimeBoxes"].style.display = "block";
    	}
    }
}
