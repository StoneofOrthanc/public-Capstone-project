import BaseClass from "../util/baseClass";
import DataStore from "../util/DataStore";
import HomeClient from "../api/homeClient";

/** This file created by another team member **/

class DeleteFreelancer extends BaseClass {

    constructor() {
        super();
        this.bindClassMethods(['onDelete'], this);
        this.dataStore = new DataStore();
    }

    async mount() {
        document.getElementById('delete-freelancer-form').addEventListener('submit', this.onDelete);
        this.client = new HomeClient();

//        this.dataStore.addChangeListener(this.renderFreelancer)
    }

// Render Methods --------------------------------------------------------------------------------------------------

//    async renderFreelancer() {
//        let resultArea = document.getElementById("freelancer-list");
//
//        const freelancers = this.dataStore.get("groups");
//
//        let htmlContent = "<ul>";
//
//        if (freelancers) {
//            for(let freelancer of freelancers){
//                htmlContent += `<li>
//                    Name: ${freelancer.name}<br>
//                    Expertise: ${freelancer.expertise}<br>
//                    Rate: ${freelancer.rate}<br>
//                    Location: ${freelancer.location}<br>
//                    Contact: ${freelancer.contact}
//                    </li>
//                `
//            }
//            htmlContent += "</ul>";
//            resultArea.innerHTML = htmlContent;
//
//        } else {
//            resultArea.innerHTML = "No Item";
//        }
//    }

// Event Handlers --------------------------------------------------------------------------------------------------

    async onDelete(event) {
        // Prevent the page from refreshing on form submit
        event.preventDefault();

        let id = document.getElementById("delete-freelancer-id-field").value;

        let freelancer = await this.client.deleteFreelancer(id, this.errorHandler);

        //this.dataStore.set("freelancer", result);

        if (freelancer == true) {
            this.showMessage(`Deleted ${id}!`)
            document.getElementById('delete-freelancer-id-field').value = "";

            const container = document.getElementById('container');
            container.classList.add('show');
        } else {
            this.errorHandler("Error deleting!  Try again...");
            document.getElementById('delete-freelancer-id-field').value = "";
        }
    }
}
/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const deleteFreelancer = new DeleteFreelancer();
    deleteFreelancer.mount();
};

window.addEventListener('DOMContentLoaded', main);