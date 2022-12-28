import Toastify from "toastify-js";

/** This file was created by Kenzie Academy **/

export default class BaseClass {
    /**
     * Binds all of the methods to "this" object. These methods will now have the state of the instance object.
     * @param methods The name of each method to bind.
     * @param classInstance The instance of the class to bind the methods to.
     */
    bindClassMethods(methods, classInstance) {
        methods.forEach(method => {
            classInstance[method] = classInstance[method].bind(classInstance);
        });
    }

    formatCurrency(amount) {
        const formatter = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
        });
        return formatter.format(amount);
    }

    /**
     * Creates a div containing all elements to display freelancer details.
     * @param book element from the datastore, which details are taken from.
     */
    createFreelancerDiv(freelancer, hireStatus) {
        let result = document.getElementById("freelancer-container");

        while (result.firstChild) {
            result.removeChild(result.firstChild);
        }

        const freelancerDiv = document.createElement('div');
        const title = document.createElement('h3');
        title.innerText = freelancer.name;

        freelancerDiv.appendChild(title);
        freelancerDiv.appendChild(document.createTextNode(`Expertise: ${freelancer.expertise}`));
        freelancerDiv.appendChild(document.createElement('br'));
        freelancerDiv.appendChild(document.createTextNode(`Hourly rate: ${freelancer.rate}`));
        freelancerDiv.appendChild(document.createElement('br'));
        freelancerDiv.appendChild(document.createTextNode(`Location: ${freelancer.location}`));
        freelancerDiv.appendChild(document.createElement('br'));
        freelancerDiv.appendChild(document.createTextNode(`Contact: ${freelancer.contact}`));
        freelancerDiv.appendChild(document.createElement('br'));
        freelancerDiv.appendChild(document.createTextNode(`Hire Status: ${hireStatus.hirestatus}`));
        freelancerDiv.appendChild(document.createElement('br'));
        freelancerDiv.appendChild(document.createElement('br'));
        result.appendChild(freelancerDiv);
    }

    createFreelancerAddDiv(freelancer) {
            let result = document.getElementById("freelancer-container");

            while (result.firstChild) {
                result.removeChild(result.firstChild);
            }

            const freelancerDiv = document.createElement('div');
            const title = document.createElement('h3');
            title.innerText = freelancer.name;

            freelancerDiv.appendChild(title);
            freelancerDiv.appendChild(document.createTextNode(`Expertise: ${freelancer.expertise}`));
            freelancerDiv.appendChild(document.createElement('br'));
            freelancerDiv.appendChild(document.createTextNode(`Hourly rate: ${freelancer.rate}`));
            freelancerDiv.appendChild(document.createElement('br'));
            freelancerDiv.appendChild(document.createTextNode(`Location: ${freelancer.location}`));
            freelancerDiv.appendChild(document.createElement('br'));
            freelancerDiv.appendChild(document.createTextNode(`Contact: ${freelancer.contact}`));
            freelancerDiv.appendChild(document.createElement('br'));
            freelancerDiv.appendChild(document.createElement('br'));
            result.appendChild(freelancerDiv);
        }

    showMessage(message) {
        Toastify({
            text: message,
            duration: 4500,
            gravity: "top",
            position: 'right',
            close: true,
            style: {
                background: "linear-gradient(to right, #00b09b, #96c93d)"
            }
        }).showToast();
    }

    errorHandler(error) {
        Toastify({
            text: error,
            duration: 4500,
            gravity: "top",
            position: 'right',
            close: true,
            style: {
                background: "linear-gradient(to right, rgb(255, 95, 109), rgb(255, 195, 113))"
            }
        }).showToast();
    }
}
