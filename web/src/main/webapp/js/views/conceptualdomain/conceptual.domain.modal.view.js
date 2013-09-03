define(['jquery', 'backbone', 'marionette', 'bootstrap', 'js/models/conceptualdomain/conceptual.domain.model', 'text!templates/conceptualdomain/conceptual.domain.modal.template.html'], function($, Backbone, Marionette, BootStrap, ConceptualDomainModel, ConceptualDomainTemplate) {
	var ConceptualDomainModalView = Marionette.ItemView.extend({
		template : ConceptualDomainTemplate,
		events : {
			'click #sendConceptualDomainForm' : 'send',
			'click #name' : 'hidePopover'
		},
		ui : {
			name : '#name',
			definition : '#definition',
			registrationStatus : '#registrationStatus',
			administrativeStatus : '#administrativeStatus',
			administrativeNote : '#administrativeNote',
			explanatoryComment : '#explanatoryComment',
			sendButton : '#sendConceptualDomainForm',
			dimensionality : '#dimensionality',
			enumerated : '#enumerated',
			title : '.modal-header h3'
		},
		initialize : function() {
			if (!this.model) {
				this.model = new ConceptualDomainModel();
			}
		},
		hidePopover : function(){
			this.$(this.ui.name).popover('hide');
		},
		fetchNewAttributes : function() {
			newAttributes = {};

			for (var field in this.ui) {
				if (!this.$(this.ui[field]).is(':disabled') && this.model.attributes.hasOwnProperty(field) && field != 'enumerated') {
					newAttributes[field] = this.ui[field].val();
				}
			}

			// enumerability should not change on update
			if (this.model.isNew()) {
				newAttributes["enumerated"] = this.ui.enumerated.is(":checked");
			}

			return newAttributes;
		},
		send : function() {
			// Checking for required fields. If empty ,popover pops up
			if (!this.ui.name.val()){
				this.$(this.ui.name).popover('show');
				return;
			}
			var self = this;
			var cdModelAttributes = this.fetchNewAttributes();
			if (this.model.changedAttributes(cdModelAttributes)){
				$('#loadingOverlay').fadeIn();
				this.model.save(cdModelAttributes, {
					wait : true,
					dataType : this.model.isNew() ? 'json' : 'text',
					success : function(response) {
						self.options.app.getMDRModelLocator().getConceptualDomainCollection().fetch();
						this.$('#modalWindow').modal('hide');
						$('#loadingOverlay').fadeOut();
					},
					error : function(error) {
						alert("Nope");
					}
				});
			}
		},
		onRender : function() {
			if (this.model.isNew()) {
				this.ui.sendButton.html('Create');
				this.ui.title.html('Create Conceptual Domain');
				this.$('#collapseOptional').hide();
			} else {
				this.ui.sendButton.html('Update');
				this.ui.title.html('Update Conceptual Domain');
				this.ui.enumerated.attr('disabled', true);
				this.ui.enumerated.attr('checked', this.model.get('enumerated'));
			}
			this.ui.registrationStatus.val(this.model.get("registrationStatus"));
			this.ui.administrativeStatus.val(this.model.get("administrativeStatus"));
			
			this.$(this.ui.name).popover({
				content : 'Please fill out this field!',
				placement : 'bottom',
				trigger : 'manual'
			});
		}
	});
	return ConceptualDomainModalView;
});
