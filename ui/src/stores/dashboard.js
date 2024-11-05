import {apiUrl} from "override/utils/route";

export default {
    namespaced: true,
    actions: {
        list(_, options) {
            const sortString = options.sort ? `?sort=${options.sort}` : ""
            delete options.sort
            return this.$http.get(`${apiUrl(this)}/dashboards${sortString}`, {
                params: options
            }).then(response => response.data);
        },
        load(_, id) {
            return this.$http.get(`${apiUrl(this)}/dashboards/${id}`).then(response => response.data);
        },
        create(_, source) {
            return this.$http.post(`${apiUrl(this)}/dashboards`, {
                body: source
            });
        },
        update(_, {id, source}) {
            return this.$http.put(`${apiUrl(this)}/dashboards/${id}`, {
                body: source
            });
        },
        delete(_, id) {
            return this.$http.delete(`${apiUrl(this)}/dashboards/${id}`);
        }
    }
}
