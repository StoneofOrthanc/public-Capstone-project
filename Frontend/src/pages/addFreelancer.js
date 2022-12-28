import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import HomeClient from "../api/homeClient";

/** This file created by another team member **/

class AddFreelancer extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['renderFreelancerDetails', 'onAdd'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('add-new-freelancer-form').addEventListener('submit', this.onAdd);
        this.client = new HomeClient();

        this.dataStore.addChangeListener(this.renderFreelancerDetails);
    }

    async renderFreelancerDetails() {
        const freelancer = this.dataStore.get("addedFreelancer");

        if (freelancer) {
            this.createFreelancerAddDiv(freelancer);
        }
    }

    async onAdd(event) {
        event.preventDefault();

        this.dataStore.set("addedFreelancer", null);

        let name = document.getElementById("add-freelancer-details-name").value;
        let expertise = document.getElementById("add-freelancer-details-expertise").value;
        let rate = document.getElementById("add-freelancer-details-rate").value;
        let location = document.getElementById("add-freelancer-details-location").value;
        let contact = document.getElementById("add-freelancer-details-contact").value;

        let request = {};
        request.name = name;
        request.expertise = expertise;
        request.rate = rate;
        request.location = location;
        request.contact = contact;

        const addedFreelancer = await this.client.addNewFreelancer(request, this.errorHandler);
        this.dataStore.set("addedFreelancer", addedFreelancer);

        if (addedFreelancer) {
            this.showMessage(`Created ${addedFreelancer.name}!`)
        } else {
            this.errorHandler("Error in creating a freelancer! Try again...");
            return;
        }
        document.getElementById('container').classList.add('show');
    }

}

const main = async () => {
    const addFreelancer = new AddFreelancer();
    await addFreelancer.mount();
};

window.addEventListener('DOMContentLoaded', main);