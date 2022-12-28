import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import HomeClient from "../api/homeClient";

/** This file created by another team member **/


/**
 * Logic needed for the view playlist page of the website.
 */
class HomePage extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onGetFreelancers', 'onCreate', 'renderFreelancers'], this);
        this.dataStore = new DataStore();
    }

    /**
     * Once the page has loaded, set up the event handlers and fetch the freelancers list.
     */
    async mount() {
        document.getElementById('get-all-freelancers-form').addEventListener('submit', this.onCreate);
        this.client = new HomeClient();
        await this.onGetFreelancers();

        this.dataStore.addChangeListener(this.renderFreelancers)
    }

    // Render Methods --------------------------------------------------------------------------------------------------

    async renderFreelancers() {
        let resultArea = document.getElementById("all-freelancers-info");

        const freelancers = this.dataStore.get("freelancers");

        let html = ""
        html += "<ul>"

        for(let freelancer of freelancers) {
           html += `
               <li>
                    <div style="font-size: 20px"><strong>${freelancer.name}</strong></div>
                    <div>ID: ${freelancer.id}</div>
                    <div>Expertise: ${freelancer.expertise}</div>
                    <br>
               </li>
           `
        }

        html += "</ul>"

        if(freelancers) {
           resultArea.innerHTML = html;
        } else {
           resultArea.innerHTML = "No Freelancers found";
        }
    }

    // Event Handlers --------------------------------------------------------------------------------------------------

    async onGetFreelancers() {
        // Prevent the page from refreshing on form submit
        let result = await this.client.getAllFreelancers(this.errorHandler);
        this.dataStore.set("freelancers", result);
    }

    async onCreate(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();
        await this.onGetFreelancers();
    }
}

     /**
      * Main method to run when the page contents have loaded.
      */
const main = async () => {
    const homePage = new HomePage();
    await homePage.mount();
};

window.addEventListener('DOMContentLoaded', main);