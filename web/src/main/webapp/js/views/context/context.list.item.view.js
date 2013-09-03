define(['marionette', 'bootbox', 'text!templates/context/context.list.item.template.html', 'js/views/context/context.modal.view'], function(Marionette, bootbox, ContextListItemViewTemplate, ContextModalView) {
	// TODO fix events.
	// TODO add click event that displays corresponding data menu
	var ContextListView = Marionette.ItemView.extend({
		template : ContextListItemViewTemplate,
		tagName : 'li',
		events : {
			'click a' : 'openContext',
			'click .icon-edit' : 'editContext',
			'click .icon-remove' : 'removeContext',
			'mouseover a' : 'showEditIcon',
			'mouseleave a' : 'hideEditIcon'
		},

		modelEvents : {
			"change" : "modelChanged"
		},

		openContext : function(e) {
			e.preventDefault();
			$('#contextSection').siblings().html(this.model.get('name') + '  <b class="caret"></b>');

			this.options.app.vent.trigger('menu:browse', this.model.get('id'));
		},

		editContext : function(e) {
			e.preventDefault();
			e.stopPropagation();
			var vent = this.options.app.vent;
			var self = this;
			vent.trigger('modalWindow:show', new ContextModalView({
				model : self.model,
				app : self.options.app
			}));
		},

		removeContext : function(e) {
			e.preventDefault();
			e.stopPropagation();
			var self = this;
			var success = function() {
				alert('Deleted');
			};

			bootbox.confirm(self.model.get('name') + ' will be deleted', function(result) {
				if (result) {
					self.model.destroy({
						async : false,
						success : success,
						error : function(jxQHR, textStatus) {
							if (textStatus.status == 200) {
								success();
							}
						}
					});
				}
			});
		},

		showEditIcon : function() {
			this.$("i").show();
		},

		hideEditIcon : function() {
			this.$("i").hide();
		},

		modelChanged : function() {
			this.options.collection.render();
		}
	});
	return ContextListView;
});
