function getSubjects(buttonApplications) {
    let div = document.getElementById('subjects');
    if (div.style.display !== 'block') {
        div.style.display = 'block';
        buttonApplications.innerHTML = '<fmt:message key="faculty.HideSubjects"/>';
    } else {
        div.style.display = 'none';
        buttonApplications.innerHTML = '<fmt:message key="faculty.DisplaySubjects"/>';
    }
}
