const checkboxes = document.querySelectorAll('.checkbox-class');

function uncheckOtherCheckboxes(event) {
  const clickedCheckbox = event.target;

  checkboxes.forEach(function(checkbox) {
    if (checkbox !== clickedCheckbox) {
      checkbox.checked = false;
    }
  });
}

checkboxes.forEach(function(checkbox) {
  checkbox.addEventListener('click', uncheckOtherCheckboxes);
});
