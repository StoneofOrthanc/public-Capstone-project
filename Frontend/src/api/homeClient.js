/** This file created by Kenzie Academy **/

import BaseClass from "../util/baseClass";
import axios from 'axios'

export default class HomeClient extends BaseClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded', 'getAllFreelancers', 'addNewFreelancer', 'deleteFreelancer', 'updateFreelancer', 'findFreelancerById'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        this.clientLoaded(axios);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        this.client = client;
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    async getAllFreelancers(errorCallback) {
        try {
            const response = await this.client.get(`/freelancers/all`);
            return response.data;
        } catch (error) {
            this.handleError("getAllFreelancers", error, errorCallback)
        }
    }

    /**
     * Gets the freelancer for the given ID.
     * @param id Unique identifier for a concert
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The concert
     */
    async findFreelancerById(id, errorCallback) {
        try {
            const response = await this.client.get(`/freelancers/${id}`);
            return response.data;
        } catch (error) {
            this.handleError("findFreelancerById", error, errorCallback)
        }
    }

    async updateFreelancer(request, errorCallback) {
        try {
            const response = await this.client.put(`/freelancers/{request.id}`, {
                id: request.id,
                name: request.name,
                expertise: request.expertise,
                rate: request.rate,
                location: request.location,
                contact: request.contact
            });
            return response.data;
        } catch (error) {
            this.handleError("updateFreelancer", error, errorCallback)
        }
    }

    async addNewFreelancer(request, errorCallback) {
        try {
            const response = await this.client.post(`/freelancers`, {
                name: request.name,
                expertise: request.expertise,
                rate: request.rate,
                location: request.location,
                contact: request.contact
            });
            return response.data;
        } catch (error) {
            this.handleError("addNewFreelancer", error, errorCallback);
        }
    }

    async deleteFreelancer(id, errorCallback) {
        try {
            const response = await this.client.delete(`/freelancers/delete/${id}`);
            return true;
        } catch (error) {
            this.handleError("deleteFreelancer", error, errorCallback)
        }
    }

    async getFreelancerHireStatus(id, errorCallback) {
        try {
            const response = await this.client.get(`/freelancers/${id}/hirestatus`);
            return response.data;
        } catch (error) {
            this.handleError("getFreelancerHireStatus", error, errorCallback)
        }
    }

    async getHireStatusChange(id, errorCallback) {
            try {
                const response = await this.client.get(`/freelancers/${id}/random`);
                return response.data;
            } catch (error) {
                this.handleError("getFreelancerHireStatus", error, errorCallback)
            }
        }
    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(method, error, errorCallback) {
        console.error(method + " failed - " + error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message);
        }
        if (errorCallback) {
            errorCallback(method + " failed - " + error);
        }
    }
}
