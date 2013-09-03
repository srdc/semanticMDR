package tr.com.srdc.mdr.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Transformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import tr.com.srdc.mdr.core.impl.ai.ClassificationSchemeImpl;
import tr.com.srdc.mdr.core.impl.ai.ConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.ConceptRelationshipImpl;
import tr.com.srdc.mdr.core.impl.ai.ContextImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementConceptImpl;
import tr.com.srdc.mdr.core.impl.ai.DataElementImpl;
import tr.com.srdc.mdr.core.impl.ai.DerivationRuleImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.EnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedConceptualDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.NonEnumeratedValueDomainImpl;
import tr.com.srdc.mdr.core.impl.ai.PropertyImpl;
import tr.com.srdc.mdr.core.impl.ai.RepresentationClassImpl;
import tr.com.srdc.mdr.core.impl.composite.AdministeredItemContextImpl;
import tr.com.srdc.mdr.core.impl.composite.AdministrationRecordImpl;
import tr.com.srdc.mdr.core.impl.composite.ClassificationSchemeItemAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.ClassificationSchemeItemImpl;
import tr.com.srdc.mdr.core.impl.composite.ClassificationSchemeItemRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.ConceptualDomainRelationshipAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.ConceptualDomainRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.ContactImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementConceptRelationshipAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementConceptRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementDerivationImpl;
import tr.com.srdc.mdr.core.impl.composite.DataElementExampleImpl;
import tr.com.srdc.mdr.core.impl.composite.DatatypeImpl;
import tr.com.srdc.mdr.core.impl.composite.DefinitionImpl;
import tr.com.srdc.mdr.core.impl.composite.DesignationImpl;
import tr.com.srdc.mdr.core.impl.composite.ItemIdentifierImpl;
import tr.com.srdc.mdr.core.impl.composite.LanguageIdentificationImpl;
import tr.com.srdc.mdr.core.impl.composite.LanguageSectionImpl;
import tr.com.srdc.mdr.core.impl.composite.OrganizationImpl;
import tr.com.srdc.mdr.core.impl.composite.PermissibleValueImpl;
import tr.com.srdc.mdr.core.impl.composite.ReferenceDocumentImpl;
import tr.com.srdc.mdr.core.impl.composite.RegistrarImpl;
import tr.com.srdc.mdr.core.impl.composite.RegistrationAuthorityIdentifierImpl;
import tr.com.srdc.mdr.core.impl.composite.RegistrationAuthorityImpl;
import tr.com.srdc.mdr.core.impl.composite.StewardshipImpl;
import tr.com.srdc.mdr.core.impl.composite.StewardshipRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.SubmissionImpl;
import tr.com.srdc.mdr.core.impl.composite.SubmissionRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.TerminologicalEntryImpl;
import tr.com.srdc.mdr.core.impl.composite.UnitOfMeasureImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueDomainRelationshipAssociationImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueDomainRelationshipImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueImpl;
import tr.com.srdc.mdr.core.impl.composite.ValueMeaningImpl;
import tr.com.srdc.mdr.core.model.iso11179.AdministeredItemResource;
import tr.com.srdc.mdr.core.model.iso11179.ClassificationSchemeResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.ConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.ContextResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementConceptResource;
import tr.com.srdc.mdr.core.model.iso11179.DataElementResource;
import tr.com.srdc.mdr.core.model.iso11179.DerivationRuleResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.EnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedConceptualDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.NonEnumeratedValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.ObjectClassResource;
import tr.com.srdc.mdr.core.model.iso11179.PropertyResource;
import tr.com.srdc.mdr.core.model.iso11179.RepresentationClassResource;
import tr.com.srdc.mdr.core.model.iso11179.ValueDomainResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministeredItemContextResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.AdministrationRecordResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ClassificationSchemeItemResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ConceptualDomainRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ContactResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementConceptRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementDerivationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DataElementExampleResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DatatypeResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DefinitionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.DesignationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ItemIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageIdentificationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.LanguageSectionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.OrganizationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.PermissibleValueResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ReferenceDocumentResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrarResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityIdentifierResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.RegistrationAuthorityResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.StewardshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.SubmissionResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.TerminologicalEntryResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.UnitOfMeasureResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipAssociationResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueDomainRelationshipResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueMeaningResource;
import tr.com.srdc.mdr.core.model.iso11179.composite.ValueResource;
import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.util.iterator.ExtendedIterator;


public class Util {

	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	private final MDRDatabase mdrDatabase;
	private final OntModel ontModel;

	public Util(MDRDatabase mdrDatabase) {
		this.mdrDatabase = mdrDatabase;
		this.ontModel = mdrDatabase.getOntModel();
		initTransformerMap();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		if (str == null || str.trim().equals("") || str.trim().equalsIgnoreCase("null")) {
			return true;
		}
		return false;
	}

	/**
	 * Creates {@link Literal} from Object
	 * 
	 * @param o
	 *            is the Object which will be {@link Literal}
	 * @return {@link Literal} object created from the Object, or null if the
	 *         Object is null
	 */
	public Literal createTypedLiteral(Object o) {
		if (o == null) {
			return null;
		}
		return ontModel.createTypedLiteral(o);
	}

	public <T extends MDRResource> List<T> createList(ExtendedIterator<? extends RDFNode> it,
			Class<T> cls) throws MDRException {

		List<T> newList = new ArrayList<T>();
		try {
			Transformer transformer = transformerMap.get(cls);
			CollectionUtils.collect(it, transformer, newList);
		} catch (Exception e) {
			throw new MDRException(e);
		}
		return newList;
	}

	@SuppressWarnings("rawtypes")
	private Map<Class, Transformer> transformerMap = new HashMap<Class, Transformer>();

	private void initTransformerMap() {
		transformerMap.put(AdministeredItemResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				RDFNode ai = ((RDFNode) arg0);
				OntClass klass = ai.as(OntClass.class);

				if (klass.hasSuperClass(mdrDatabase.getVocabulary().ClassificationScheme)) {
					return new ClassificationSchemeImpl(ai.asResource(),
							mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().Concept)) {
					return new ConceptImpl(ai.asResource(), mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().ConceptRelationship)) {
					return new ConceptRelationshipImpl(ai.asResource(),
							mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().Context)) {
					return new ContextImpl(ai.asResource(), mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().DataElementConcept)) {
					return new DataElementConceptImpl(ai.asResource(),
							mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().DataElement)) {
					return new DataElementImpl(ai.asResource(), mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().DerivationRule)) {
					return new DerivationRuleImpl(ai.asResource(), mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedConceptualDomain)) {
					return new EnumeratedConceptualDomainImpl(ai.asResource(),
							mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain)) {
					return new EnumeratedValueDomainImpl(ai.asResource(),
							mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().NonEnumeratedConceptualDomain)) {
					return new NonEnumeratedConceptualDomainImpl(ai
							.asResource(), mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().NonEnumeratedValueDomain)) {
					return new NonEnumeratedValueDomainImpl(ai.asResource(),
							mdrDatabase);
				} else if (klass.hasSuperClass(mdrDatabase.getVocabulary().Property)) {
					return new PropertyImpl(ai.asResource(), mdrDatabase);
				} else {
					return new RepresentationClassImpl(ai.asResource(),
							mdrDatabase);
				}
			}
		});

		transformerMap.put(ClassificationSchemeResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ClassificationSchemeImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ConceptResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new ConceptImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(ConceptRelationshipResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ConceptRelationshipImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ConceptualDomainResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				RDFNode ai = ((RDFNode) arg0);
				OntClass klass = ai.as(OntClass.class);

				if (klass.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedConceptualDomain)) {
					return new EnumeratedConceptualDomainImpl(ai.asResource(),
							mdrDatabase);
				} else {
					return new NonEnumeratedConceptualDomainImpl(ai
							.asResource(), mdrDatabase);
				}
			}
		});

		transformerMap.put(ContextResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new ContextImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(DataElementConceptResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new DataElementConceptImpl(
						((RDFNode) arg0).asResource(), mdrDatabase);
			}
		});

		transformerMap.put(DataElementResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new DataElementImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(DerivationRuleResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new DerivationRuleImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});
		transformerMap.put(EnumeratedConceptualDomainResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new EnumeratedConceptualDomainImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(EnumeratedValueDomainResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new EnumeratedValueDomainImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(NonEnumeratedConceptualDomainResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new NonEnumeratedConceptualDomainImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(NonEnumeratedValueDomainResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new NonEnumeratedValueDomainImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ObjectClassResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				RDFNode ai = ((RDFNode) arg0);
				OntClass klass = ai.as(OntClass.class);

				if (klass.hasSuperClass(mdrDatabase.getVocabulary().Concept)) {
					return new ConceptImpl(ai.asResource(), mdrDatabase);
				} else {
					return new ConceptRelationshipImpl(ai.asResource(),
							mdrDatabase);
				}
			}
		});

		transformerMap.put(PropertyResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new PropertyImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(RepresentationClassResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new RepresentationClassImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ValueDomainResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				RDFNode ai = ((RDFNode) arg0);
				OntClass klass = ai.as(OntClass.class);

				if (klass.hasSuperClass(mdrDatabase.getVocabulary().EnumeratedValueDomain)) {
					return new EnumeratedValueDomainImpl(ai.asResource(),
							mdrDatabase);
				} else {
					return new NonEnumeratedValueDomainImpl(ai.asResource(),
							mdrDatabase);
				}
			}
		});

		transformerMap.put(AdministeredItemContextResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new AdministeredItemContextImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(AdministrationRecordResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new AdministrationRecordImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ClassificationSchemeItemAssociationResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ClassificationSchemeItemAssociationImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ClassificationSchemeItemResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ClassificationSchemeItemImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ClassificationSchemeItemRelationshipResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ClassificationSchemeItemRelationshipImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(
				ConceptualDomainRelationshipAssociationResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ConceptualDomainRelationshipAssociationImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ConceptualDomainRelationshipResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ConceptualDomainRelationshipImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ContactResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new ContactImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});
		transformerMap.put(
				DataElementConceptRelationshipAssociationResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new DataElementConceptRelationshipAssociationImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});
		transformerMap.put(DataElementConceptRelationshipResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new DataElementConceptRelationshipImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(DataElementDerivationResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new DataElementDerivationImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(DataElementExampleResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new DataElementExampleImpl(
						((RDFNode) arg0).asResource(), mdrDatabase);
			}
		});

		transformerMap.put(DatatypeResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new DatatypeImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(DefinitionResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new DefinitionImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(DesignationResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new DesignationImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(ItemIdentifierResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new ItemIdentifierImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(LanguageIdentificationResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new LanguageIdentificationImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});
		transformerMap.put(LanguageSectionResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new LanguageSectionImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(OrganizationResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new OrganizationImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(PermissibleValueResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new PermissibleValueImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(ReferenceDocumentResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new ReferenceDocumentImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(RegistrarResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new RegistrarImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(RegistrationAuthorityIdentifierResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new RegistrationAuthorityIdentifierImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(RegistrationAuthorityResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new RegistrationAuthorityImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(StewardshipResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new StewardshipImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(StewardshipRelationshipResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new StewardshipRelationshipImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(SubmissionResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new SubmissionImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(SubmissionRelationshipResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new SubmissionRelationshipImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(TerminologicalEntryResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new TerminologicalEntryImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(UnitOfMeasureResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new UnitOfMeasureImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		transformerMap.put(ValueDomainRelationshipAssociationResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ValueDomainRelationshipAssociationImpl(
								((RDFNode) arg0).asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ValueDomainRelationshipResource.class,
				new Transformer() {
					@Override
					public Object transform(Object arg0) {
						return new ValueDomainRelationshipImpl(((RDFNode) arg0)
								.asResource(), mdrDatabase);
					}
				});

		transformerMap.put(ValueResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new ValueImpl(((RDFNode) arg0).asResource(), mdrDatabase);
			}
		});

		transformerMap.put(ValueMeaningResource.class, new Transformer() {
			@Override
			public Object transform(Object arg0) {
				return new ValueMeaningImpl(((RDFNode) arg0).asResource(),
						mdrDatabase);
			}
		});

		logger.info("Transformer map for Util is initialized");
	}

}
