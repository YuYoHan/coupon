$(function(){
	
	mobiscroll.setOptions({
		theme: 'ios',
		themeVariant: 'light'
	});
	
	
	mobiscroll.datepicker('#demo-range-selection', {
		controls: ['calendar'],
		display: 'inline',
		rangeSelectMode: 'wizard',
		select: 'range',
		showRangeLabels: true
	});
})




