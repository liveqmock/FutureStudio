define([
    'jquery',
    'underscore',
    'backbone',
    'core',
    'modules/main/view/MenuView',
    'modules/base/collection/BaseCollection',
    'text!../tpl/NavMenu.html'
],function($, _, Backbone, core, MenuView, Collection, tpl){
    return Backbone.View.extend({
        tagName:"li",
        className:"dropdown",
        template:_.template(tpl),
        initialize:function(){
            this.collection = new Collection();
            this.collection.comparator = function(menu){
                return menu.get("priority");
            };
        },
        render:function(){
            var that = this;
            this.$el.append(this.template(this.model.toJSON()));
            this.collection.add(this.model.attributes.children);
            this.collection.each(function(menu){
                that.$(".dropdown-menu").append(new MenuView({model:menu}).render().el);
            });
            return this;
        }
    });
});