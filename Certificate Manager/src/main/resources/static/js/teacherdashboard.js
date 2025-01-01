document.addEventListener('DOMContentLoaded', () => {
    const checkboxes = document.querySelectorAll('.status-checkbox');
    const overallStatusCells = document.querySelectorAll('.overall-status');
    const confirmButton = document.getElementById('Confirm');

    // Store initial overall statuses
    let initialOverallStatuses = Array.from(overallStatusCells).map(cell => cell.innerText);

    // Load checkbox states from localStorage
    loadCheckboxStates();

    // Event listener for checkbox changes
    checkboxes.forEach((checkbox, index) => {
        checkbox.addEventListener('change', () => {
            updateOverallStatus(index); // Update status for the current student
            checkOverallStatusChange(); // Check if any change occurred
            saveCheckboxState(); // Save checkbox states to localStorage
        });
    });

    // Update the overall status for the given student (based on index)
    function updateOverallStatus(index) {
        const row = checkboxes[index].closest('tr');
        const checkboxesInRow = row.querySelectorAll('.status-checkbox');
        const overallStatusCell = row.querySelector('.overall-status');
        
        let allChecked = true;

        checkboxesInRow.forEach((checkbox) => {
            if (!checkbox.checked) {
                allChecked = false;
            }
        });

        overallStatusCell.innerText = allChecked ? "pass" : "not pass";
        overallStatusCell.style.color = allChecked ? 'green' : 'red';
    }

    // Check if any overall status has changed compared to the initial state
    function checkOverallStatusChange() {
        let anyChange = false;

        overallStatusCells.forEach((cell, index) => {
            if (cell.innerText !== initialOverallStatuses[index]) {
                anyChange = true;
            }
        });

        // Enable the "Apply Changes" button if any student's status has changed
        confirmButton.disabled = !anyChange;
    }

    // Event listener for the "Apply Changes" button click
    confirmButton.addEventListener('click', () => {
        confirmButton.disabled = true;

        // Update initial overall statuses to the current ones after changes are applied
        overallStatusCells.forEach((cell, index) => {
            initialOverallStatuses[index] = cell.innerText;
        });

        // Optionally, you can handle further actions like sending data to the server

        // Save current checkbox states after applying changes
        saveCheckboxState();
    });

    // Save checkbox states to localStorage
    function saveCheckboxState() {
        const checkboxStates = Array.from(checkboxes).map(checkbox => checkbox.checked);
        localStorage.setItem('checkboxStates', JSON.stringify(checkboxStates));
    }

    // Load checkbox states from localStorage
    function loadCheckboxStates() {
        const savedStates = JSON.parse(localStorage.getItem('checkboxStates'));

        if (savedStates) {
            checkboxes.forEach((checkbox, index) => {
                checkbox.checked = savedStates[index];
                updateOverallStatus(index); // Update the overall status after loading the checkbox state
            });
        }
    }
});
