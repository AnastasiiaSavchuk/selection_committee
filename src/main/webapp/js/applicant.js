const status = document.querySelectorAll('.getStatus');
const updateHandler = i => {
    document.getElementById("applicantUpdate").value = i.getAttribute("value");
};
status.forEach(i => i.onclick = () => updateHandler(i));

function applicantUpdate() {
    let applicantToUpdate = document.getElementById('applicantUpdate');
    if (applicantToUpdate.value === '') {
        // modalAlert();
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

function getApplications(buttonApplications) {
    let div = document.getElementById('application');
    if (div.style.display !== 'block') {
        div.style.display = 'block';
        buttonApplications.innerHTML = '<fmt:message key="faculty.HideApplication"/>';
    } else {
        div.style.display = 'none';
        buttonApplications.innerHTML = '<fmt:message key="faculty.DisplayApplication"/>';
    }
}