import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import HomeClient from "../api/homeClient";

/** This file created by another team member and implemented by Raymond Morales **/

class UpdateFreelancer extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['renderFreelancerDetails', 'onUpdate'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('update-freelancer-details-form').addEventListener('submit', this.onUpdate);
        this.client = new HomeClient();

        this.dataStore.addChangeListener(this.renderFreelancerDetails);
    }

    async renderFreelancerDetails() {
        const freelancer = this.dataStore.get("updatedFreelancer");

        if (freelancer) {
            this.createFreelancerAddDiv(freelancer);
        }
    }

    async onUpdate(event) {
        event.preventDefault();

        let id = document.getElementById("update-freelancer-details-id").value;

        this.dataStore.set("freelancerFromBackend", null);
        const freelancerFromBackend = await this.client.findFreelancerById(id, this.errorHandler);

        if (!freelancerFromBackend) {
            this.errorHandler("Error finding freelancer! Try again...");
            this.resetValues();
            return;
        }

        this.dataStore.set("freelancerFromBackend", freelancerFromBackend);

        let name = document.getElementById("update-freelancer-details-name").value;
        let expertise = document.getElementById("update-freelancer-details-expertise").value;
        let rate = document.getElementById("update-freelancer-details-rate").value;
        let location = document.getElementById("update-freelancer-details-location").value;
        let contact = document.getElementById("update-freelancer-details-contact").value;

        let request = {};
        request.id = id;
        request.name = name;
        request.expertise = expertise.length > 0 ? expertise : freelancerFromBackend.expertise;
        request.rate = rate.length > 0 ? rate : freelancerFromBackend.rate;
        request.location = location.length > 0 ? location : freelancerFromBackend.location;
        request.contact = contact.length > 0 ? contact : freelancerFromBackend.contact;

        this.dataStore.set("updatedFreelancer", null);
        const updatedFreelancer = await this.client.updateFreelancer(request, this.errorHandler);

        if (!updatedFreelancer) {
            this.errorHandler("Error updating freelancer! Try again...");
            this.resetValues();
            return;
        }

        this.dataStore.set("updatedFreelancer", updatedFreelancer);
        document.getElementById('container').classList.add('show');
        this.resetValues();
    }

    resetValues() {
        document.getElementById("update-freelancer-details-id").value = "";
        document.getElementById("update-freelancer-details-name").value = "";
        document.getElementById("update-freelancer-details-expertise").value = "";
        document.getElementById("update-freelancer-details-rate").value = "";
        document.getElementById("update-freelancer-details-location").value = "";
        document.getElementById("update-freelancer-details-contact").value = "";
    }

}

const main = async () => {
    const updateFreelancer = new UpdateFreelancer();
    await updateFreelancer.mount();
};

window.addEventListener('DOMContentLoaded', main);