const status = document.querySelectorAll('.getStatus');
const updateHandler = i => {
    document.getElementById("applicantUpdate").value = i.getAttribute("value");
};
status.forEach(i => i.onclick = () => updateHandler(i));

function applicantUpdate() {
    let applicantToUpdate = document.getElementById('applicantUpdate');
    if (applicantToUpdate.value === '') {
        return false;
    } else {
        document.getElementById('updateForm').submit();
    }
}

function getCertificate(buttonCertificate) {
    let div = document.getElementById('certificate');
    if (div.style.display !== 'block') {
        div.style.display = 'block';
        buttonCertificate.innerHTML = '<fmt:message key="applicant.HideCertificate"/>';
    } else {
        div.style.display = 'none';
        buttonCertificate.innerHTML = '<fmt:message key="applicant.DisplayCertificate"/>';
    }
}

function getGrades(buttonGrades) {
    let div = document.getElementById('grade');
    if (div.style.display !== 'block') {
        div.style.display = 'block';
        buttonGrades.innerHTML = '<fmt:message key="grade.HideGrade"/>';
    } else {
        div.style.display = 'none';
        buttonGrades.innerHTML = '<fmt:message key="grade.DisplayGrade"/>';
    }
}

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
