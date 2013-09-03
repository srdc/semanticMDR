package tr.com.srdc.mdr.core.model;

import tr.com.srdc.mdr.core.store.MDRDatabase;

import com.hp.hpl.jena.enhanced.EnhGraph;
import com.hp.hpl.jena.graph.Node;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.impl.OntClassImpl;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.OWL;


/**
 * 
 * Abstract class to implement the common methods for ontological resources.
 * 
 * @author anil
 * 
 */
public abstract class AbstractMDRResource extends OntClassImpl implements MDRResource {

	protected MDRDatabase mdrDatabase;

	public AbstractMDRResource(Node n, EnhGraph g, MDRDatabase mdrDatabase) {
		super(n,g);
		this.setRDFType(OWL.Class);
		this.mdrDatabase = mdrDatabase;
	}

	public AbstractMDRResource(Resource resource, MDRDatabase mdrDatabase) {
		super(resource.asNode(),
				(EnhGraph) mdrDatabase.getOntModel());
		this.mdrDatabase = mdrDatabase;
	}

	@Override
	public void delete() {
		remove();
	}

	@Override
	public OntClass asJenaClass() {
		return this;
	}

//	// ///////////////////////////////////
//	// // For OntClassImpl methods //////
//	// /////////////////////////////////
//
//	@Override
//	public void addDisjointWith(Resource cls) {
//		ontClassImpl.addDisjointWith(cls);
//	}
//
//	@Override
//	public void addEquivalentClass(Resource cls) {
//		ontClassImpl.addEquivalentClass(cls);
//	}
//
//	@Override
//	public void addSubClass(Resource cls) {
//		ontClassImpl.addSubClass(cls);
//	}
//
//	@Override
//	public void addSuperClass(Resource cls) {
//		ontClassImpl.addSuperClass(cls);
//	}
//
//	@Override
//	public ComplementClass asComplementClass() {
//		return ontClassImpl.asComplementClass();
//	}
//
//	@Override
//	public EnumeratedClass asEnumeratedClass() {
//		return ontClassImpl.asEnumeratedClass();
//	}
//
//	@Override
//	public IntersectionClass asIntersectionClass() {
//		return ontClassImpl.asIntersectionClass();
//	}
//
//	@Override
//	public Restriction asRestriction() {
//		return ontClassImpl.asRestriction();
//	}
//
//	@Override
//	public UnionClass asUnionClass() {
//		return ontClassImpl.asUnionClass();
//	}
//
//	@Override
//	public ComplementClass convertToComplementClass(Resource cls) {
//		return ontClassImpl.convertToComplementClass(cls);
//	}
//
//	@Override
//	public EnumeratedClass convertToEnumeratedClass(RDFList individuals) {
//		return ontClassImpl.convertToEnumeratedClass(individuals);
//	}
//
//	@Override
//	public IntersectionClass convertToIntersectionClass(RDFList classes) {
//		return ontClassImpl.convertToIntersectionClass(classes);
//	}
//
//	@Override
//	public Restriction convertToRestriction(Property prop) {
//		return ontClassImpl.convertToRestriction(prop);
//	}
//
//	@Override
//	public UnionClass convertToUnionClass(RDFList classes) {
//		return ontClassImpl.convertToUnionClass(classes);
//	}
//
//	@Override
//	public Individual createIndividual() {
//		return ontClassImpl.createIndividual();
//	}
//
//	@Override
//	public Individual createIndividual(String uri) {
//		return ontClassImpl.createIndividual(uri);
//	}
//
//	@Override
//	public void dropIndividual(Resource individual) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.dropIndividual(individual);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public OntClass getDisjointWith() {
//		return ontClassImpl.getDisjointWith();
//	}
//
//	@Override
//	public OntClass getEquivalentClass() {
//		return ontClassImpl.getEquivalentClass();
//	}
//
//	@Override
//	public OntClass getSubClass() {
//		return ontClassImpl.getSubClass();
//	}
//
//	@Override
//	public OntClass getSuperClass() {
//		return ontClassImpl.getSuperClass();
//	}
//
//	@Override
//	public boolean hasDeclaredProperty(Property p, boolean direct) {
//		return ontClassImpl.hasDeclaredProperty(p, direct);
//	}
//
//	@Override
//	public boolean hasEquivalentClass(Resource cls) {
//		return ontClassImpl.hasEquivalentClass(cls);
//	}
//
//	@Override
//	public boolean hasSubClass() {
//		return ontClassImpl.hasSubClass();
//	}
//
//	@Override
//	public boolean hasSubClass(Resource cls) {
//		return ontClassImpl.hasSubClass(cls);
//	}
//
//	@Override
//	public boolean hasSubClass(Resource cls, boolean direct) {
//		return ontClassImpl.hasSubClass(cls, direct);
//	}
//
//	@Override
//	public boolean hasSuperClass() {
//		return ontClassImpl.hasSuperClass();
//	}
//
//	@Override
//	public boolean hasSuperClass(Resource cls) {
//		return ontClassImpl.hasSuperClass(cls);
//	}
//
//	@Override
//	public boolean hasSuperClass(Resource cls, boolean direct) {
//		return ontClassImpl.hasSuperClass(cls, direct);
//	}
//
//	@Override
//	public boolean isComplementClass() {
//		return ontClassImpl.isComplementClass();
//	}
//
//	@Override
//	public boolean isDisjointWith(Resource cls) {
//		return ontClassImpl.isDisjointWith(cls);
//	}
//
//	@Override
//	public boolean isEnumeratedClass() {
//		return ontClassImpl.isEnumeratedClass();
//	}
//
//	@Override
//	public boolean isHierarchyRoot() {
//		return ontClassImpl.isHierarchyRoot();
//	}
//
//	@Override
//	public boolean isIntersectionClass() {
//		return ontClassImpl.isIntersectionClass();
//	}
//
//	@Override
//	public boolean isRestriction() {
//		return ontClassImpl.isRestriction();
//	}
//
//	@Override
//	public boolean isUnionClass() {
//		return ontClassImpl.isUnionClass();
//	}
//
//	@Override
//	public ExtendedIterator<OntProperty> listDeclaredProperties() {
//		return ontClassImpl.listDeclaredProperties();
//	}
//
//	@Override
//	public ExtendedIterator<OntProperty> listDeclaredProperties(boolean direct) {
//		return ontClassImpl.listDeclaredProperties(direct);
//	}
//
//	@Override
//	public ExtendedIterator<OntClass> listDisjointWith() {
//		return ontClassImpl.listDisjointWith();
//	}
//
//	@Override
//	public ExtendedIterator<OntClass> listEquivalentClasses() {
//		return ontClassImpl.listEquivalentClasses();
//	}
//
//	@Override
//	public ExtendedIterator<? extends OntResource> listInstances() {
//		return ontClassImpl.listInstances();
//	}
//
//	@Override
//	public ExtendedIterator<? extends OntResource> listInstances(boolean direct) {
//		return ontClassImpl.listInstances(direct);
//	}
//
//	@Override
//	public ExtendedIterator<OntClass> listSubClasses() {
//		return ontClassImpl.listSubClasses();
//	}
//
//	@Override
//	public ExtendedIterator<OntClass> listSubClasses(boolean direct) {
//		return ontClassImpl.listSubClasses(direct);
//	}
//
//	@Override
//	public ExtendedIterator<OntClass> listSuperClasses() {
//		return ontClassImpl.listSuperClasses();
//	}
//
//	@Override
//	public ExtendedIterator<OntClass> listSuperClasses(boolean direct) {
//		return ontClassImpl.listSuperClasses(direct);
//	}
//
//	@Override
//	public void removeDisjointWith(Resource cls) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeDisjointWith(cls);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeEquivalentClass(Resource cls) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeEquivalentClass(cls);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeSubClass(Resource cls) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeSubClass(cls);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeSuperClass(Resource cls) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeSuperClass(cls);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void setDisjointWith(Resource cls) {
//		ontClassImpl.setDisjointWith(cls);
//	}
//
//	@Override
//	public void setEquivalentClass(Resource cls) {
//		ontClassImpl.setEquivalentClass(cls);
//	}
//
//	@Override
//	public void setSubClass(Resource cls) {
//		ontClassImpl.setSubClass(cls);
//	}
//
//	@Override
//	public void setSuperClass(Resource cls) {
//		ontClassImpl.setSuperClass(cls);
//	}
//
//	@Override
//	public void addComment(Literal comment) {
//		ontClassImpl.addComment(comment);
//	}
//
//	@Override
//	public void addComment(String comment, String lang) {
//		ontClassImpl.addComment(comment, lang);
//	}
//
//	@Override
//	public void addDifferentFrom(Resource res) {
//		ontClassImpl.addDifferentFrom(res);
//	}
//
//	@Override
//	public void addIsDefinedBy(Resource res) {
//		ontClassImpl.addIsDefinedBy(res);
//	}
//
//	@Override
//	public void addLabel(Literal label) {
//		ontClassImpl.addLabel(label);
//	}
//
//	@Override
//	public void addLabel(String label, String lang) {
//		ontClassImpl.addLabel(label, lang);
//	}
//
//	@Override
//	public void addRDFType(Resource cls) {
//		ontClassImpl.addRDFType(cls);
//	}
//
//	@Override
//	public void addSameAs(Resource res) {
//		ontClassImpl.addSameAs(res);
//	}
//
//	@Override
//	public void addSeeAlso(Resource res) {
//		ontClassImpl.addSeeAlso(res);
//	}
//
//	@Override
//	public void addVersionInfo(String info) {
//		ontClassImpl.addVersionInfo(info);
//	}
//
//	@Override
//	public AllDifferent asAllDifferent() {
//		return ontClassImpl.asAllDifferent();
//	}
//
//	@Override
//	public AnnotationProperty asAnnotationProperty() {
//		return ontClassImpl.asAnnotationProperty();
//	}
//
//	@Override
//	public OntClass asClass() {
//		return ontClassImpl.asClass();
//	}
//
//	@Override
//	public DataRange asDataRange() {
//		return ontClassImpl.asDataRange();
//	}
//
//	@Override
//	public DatatypeProperty asDatatypeProperty() {
//		return ontClassImpl.asDatatypeProperty();
//	}
//
//	@Override
//	public Individual asIndividual() {
//		return ontClassImpl.asIndividual();
//	}
//
//	@Override
//	public ObjectProperty asObjectProperty() {
//		return ontClassImpl.asObjectProperty();
//	}
//
//	@Override
//	public Ontology asOntology() {
//		return ontClassImpl.asOntology();
//	}
//
//	@Override
//	public OntProperty asProperty() {
//		return ontClassImpl.asProperty();
//	}
//
//	@Override
//	public int getCardinality(Property p) {
//		return ontClassImpl.getCardinality(p);
//	}
//
//	@Override
//	public String getComment(String lang) {
//		return ontClassImpl.getComment(lang);
//	}
//
//	@Override
//	public OntResource getDifferentFrom() {
//		return ontClassImpl.getDifferentFrom();
//	}
//
//	@Override
//	public Resource getIsDefinedBy() {
//		return ontClassImpl.getIsDefinedBy();
//	}
//
//	@Override
//	public String getLabel(String lang) {
//		return ontClassImpl.getLabel(lang);
//	}
//
//	@Override
//	public OntModel getOntModel() {
//		return ontClassImpl.getOntModel();
//	}
//
//	@Override
//	public Profile getProfile() {
//		return ontClassImpl.getProfile();
//	}
//
//	@Override
//	public RDFNode getPropertyValue(Property property) {
//		return ontClassImpl.getPropertyValue(property);
//	}
//
//	@Override
//	public Resource getRDFType() {
//		return ontClassImpl.getRDFType();
//	}
//
//	@Override
//	public Resource getRDFType(boolean direct) {
//		return ontClassImpl.getRDFType(direct);
//	}
//
//	@Override
//	public OntResource getSameAs() {
//		return ontClassImpl.getSameAs();
//	}
//
//	@Override
//	public Resource getSeeAlso() {
//		return ontClassImpl.getSeeAlso();
//	}
//
//	@Override
//	public String getVersionInfo() {
//		return ontClassImpl.getVersionInfo();
//	}
//
//	@Override
//	public boolean hasComment(Literal comment) {
//		return ontClassImpl.hasComment(comment);
//	}
//
//	@Override
//	public boolean hasComment(String comment, String lang) {
//		return ontClassImpl.hasComment(comment, lang);
//	}
//
//	@Override
//	public boolean hasLabel(Literal label) {
//		return ontClassImpl.hasLabel(label);
//	}
//
//	@Override
//	public boolean hasLabel(String label, String lang) {
//		return ontClassImpl.hasLabel(label, lang);
//	}
//
//	@Override
//	public boolean hasRDFType(Resource ontClass) {
//		return ontClassImpl.hasRDFType(ontClass);
//	}
//
//	@Override
//	public boolean hasRDFType(String uri) {
//		return ontClassImpl.hasRDFType(uri);
//	}
//
//	@Override
//	public boolean hasRDFType(Resource ontClass, boolean direct) {
//		return ontClassImpl.hasRDFType(ontClass, direct);
//	}
//
//	@Override
//	public boolean hasSeeAlso(Resource res) {
//		return ontClassImpl.hasSeeAlso(res);
//	}
//
//	@Override
//	public boolean hasVersionInfo(String info) {
//		return ontClassImpl.hasVersionInfo(info);
//	}
//
//	@Override
//	public boolean isAllDifferent() {
//		return ontClassImpl.isAllDifferent();
//	}
//
//	@Override
//	public boolean isAnnotationProperty() {
//		return ontClassImpl.isAnnotationProperty();
//	}
//
//	@Override
//	public boolean isClass() {
//		return ontClassImpl.isClass();
//	}
//
//	@Override
//	public boolean isDataRange() {
//		return ontClassImpl.isDataRange();
//	}
//
//	@Override
//	public boolean isDatatypeProperty() {
//		return ontClassImpl.isDatatypeProperty();
//	}
//
//	@Override
//	public boolean isDefinedBy(Resource res) {
//		return ontClassImpl.isDefinedBy(res);
//	}
//
//	@Override
//	public boolean isDifferentFrom(Resource res) {
//		return ontClassImpl.isDifferentFrom(res);
//	}
//
//	@Override
//	public boolean isIndividual() {
//		return ontClassImpl.isIndividual();
//	}
//
//	@Override
//	public boolean isObjectProperty() {
//		return ontClassImpl.isObjectProperty();
//	}
//
//	@Override
//	public boolean isOntLanguageTerm() {
//		return ontClassImpl.isOntLanguageTerm();
//	}
//
//	@Override
//	public boolean isOntology() {
//		return ontClassImpl.isOntology();
//	}
//
//	@Override
//	public boolean isProperty() {
//		return ontClassImpl.isProperty();
//	}
//
//	@Override
//	public boolean isSameAs(Resource res) {
//		return ontClassImpl.isSameAs(res);
//	}
//
//	@Override
//	public ExtendedIterator<RDFNode> listComments(String lang) {
//		return ontClassImpl.listComments(lang);
//	}
//
//	@Override
//	public ExtendedIterator<? extends Resource> listDifferentFrom() {
//		return ontClassImpl.listDifferentFrom();
//	}
//
//	@Override
//	public ExtendedIterator<RDFNode> listIsDefinedBy() {
//		return ontClassImpl.listIsDefinedBy();
//	}
//
//	@Override
//	public ExtendedIterator<RDFNode> listLabels(String lang) {
//		return ontClassImpl.listLabels(lang);
//	}
//
//	@Override
//	public NodeIterator listPropertyValues(Property property) {
//		return ontClassImpl.listPropertyValues(property);
//	}
//
//	@Override
//	public ExtendedIterator<Resource> listRDFTypes(boolean direct) {
//		return ontClassImpl.listRDFTypes(direct);
//	}
//
//	@Override
//	public ExtendedIterator<? extends Resource> listSameAs() {
//		return ontClassImpl.listSameAs();
//	}
//
//	@Override
//	public ExtendedIterator<RDFNode> listSeeAlso() {
//		return ontClassImpl.listSeeAlso();
//	}
//
//	@Override
//	public ExtendedIterator<String> listVersionInfo() {
//		return ontClassImpl.listVersionInfo();
//	}
//
//	@Override
//	public void remove() {
//		mdrDatabase.beginWrite();
//		ontClassImpl.remove();
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeComment(Literal comment) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeComment(comment);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeComment(String comment, String lang) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeComment(comment, lang);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeDefinedBy(Resource res) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeDefinedBy(res);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeDifferentFrom(Resource res) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeDifferentFrom(res);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeLabel(Literal label) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeLabel(label);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeLabel(String label, String lang) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeLabel(label, lang);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeProperty(Property property, RDFNode value) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeProperty(property, value);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeRDFType(Resource cls) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeRDFType(cls);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeSameAs(Resource res) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeSameAs(res);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeSeeAlso(Resource res) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeSeeAlso(res);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void removeVersionInfo(String info) {
//		mdrDatabase.beginWrite();
//		ontClassImpl.removeVersionInfo(info);
//		mdrDatabase.commitend();
//	}
//
//	@Override
//	public void setComment(String comment, String lang) {
//		ontClassImpl.setComment(comment, lang);
//	}
//
//	@Override
//	public void setDifferentFrom(Resource res) {
//		ontClassImpl.setDifferentFrom(res);
//	}
//
//	@Override
//	public void setIsDefinedBy(Resource res) {
//		ontClassImpl.setIsDefinedBy(res);
//	}
//
//	@Override
//	public void setLabel(String label, String lang) {
//		ontClassImpl.setLabel(label, lang);
//	}
//
//	@Override
//	public void setPropertyValue(Property property, RDFNode value) {
//		ontClassImpl.setPropertyValue(property, value);
//	}
//
//	@Override
//	public void setRDFType(Resource cls) {
//		ontClassImpl.setRDFType(cls);
//	}
//
//	@Override
//	public void setSameAs(Resource res) {
//		ontClassImpl.setSameAs(res);
//	}
//
//	@Override
//	public void setSeeAlso(Resource res) {
//		ontClassImpl.setSeeAlso(res);
//	}
//
//	@Override
//	public void setVersionInfo(String info) {
//		ontClassImpl.setVersionInfo(info);
//	}
//
//	@Override
//	public Resource abort() {
//		return ontClassImpl.abort();
//	}
//
//	@Override
//	public Resource addLiteral(Property p, boolean o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addLiteral(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addLiteral(Property p, long o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addLiteral(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addLiteral(Property p, char o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addLiteral(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addLiteral(Property p, double o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addLiteral(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addLiteral(Property p, float o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addLiteral(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addLiteral(Property p, Object o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addLiteral(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addLiteral(Property p, Literal o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addLiteral(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addProperty(Property p, String o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addProperty(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addProperty(Property p, RDFNode o) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addProperty(p, o);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addProperty(Property p, String o, String l) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addProperty(p, o, l);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource addProperty(Property p, String lexicalForm,
//			RDFDatatype datatype) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.addProperty(p, lexicalForm, datatype);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource begin() {
//		return ontClassImpl.begin();
//	}
//
//	@Override
//	public Resource commit() {
//		return ontClassImpl.commit();
//	}
//
//	@Override
//	public AnonId getId() {
//		return ontClassImpl.getId();
//	}
//
//	@Override
//	public String getLocalName() {
//		return ontClassImpl.getLocalName();
//	}
//
//	@Override
//	public String getNameSpace() {
//		return ontClassImpl.getNameSpace();
//	}
//
//	@Override
//	public Statement getProperty(Property p) {
//		return ontClassImpl.getProperty(p);
//	}
//
//	@Override
//	public Resource getPropertyResourceValue(Property p) {
//		return ontClassImpl.getPropertyResourceValue(p);
//	}
//
//	@Override
//	public Statement getRequiredProperty(Property p) {
//		return ontClassImpl.getRequiredProperty(p);
//	}
//
//	@Override
//	public String getURI() {
//		return ontClassImpl.getURI();
//	}
//
//	@Override
//	public boolean hasLiteral(Property p, boolean o) {
//		return ontClassImpl.hasLiteral(p, o);
//	}
//
//	@Override
//	public boolean hasLiteral(Property p, long o) {
//		return ontClassImpl.hasLiteral(p, o);
//	}
//
//	@Override
//	public boolean hasLiteral(Property p, char o) {
//		return ontClassImpl.hasLiteral(p, o);
//	}
//
//	@Override
//	public boolean hasLiteral(Property p, double o) {
//		return ontClassImpl.hasLiteral(p, o);
//	}
//
//	@Override
//	public boolean hasLiteral(Property p, float o) {
//		return ontClassImpl.hasLiteral(p, o);
//	}
//
//	@Override
//	public boolean hasLiteral(Property p, Object o) {
//		return ontClassImpl.hasLiteral(p, o);
//	}
//
//	@Override
//	public boolean hasProperty(Property p) {
//		return ontClassImpl.hasProperty(p);
//	}
//
//	@Override
//	public boolean hasProperty(Property p, String o) {
//		return ontClassImpl.hasProperty(p, o);
//	}
//
//	@Override
//	public boolean hasProperty(Property p, RDFNode o) {
//		return ontClassImpl.hasProperty(p, o);
//	}
//
//	@Override
//	public boolean hasProperty(Property p, String o, String l) {
//		return ontClassImpl.hasProperty(p, o, l);
//	}
//
//	@Override
//	public boolean hasURI(String uri) {
//		return ontClassImpl.hasURI(uri);
//	}
//
//	@Override
//	public Resource inModel(Model m) {
//		return ontClassImpl.inModel(m);
//	}
//
//	@Override
//	public StmtIterator listProperties() {
//		return ontClassImpl.listProperties();
//	}
//
//	@Override
//	public StmtIterator listProperties(Property p) {
//		return ontClassImpl.listProperties(p);
//	}
//
//	@Override
//	public Resource removeAll(Property p) {
//		mdrDatabase.beginWrite();
//		Resource resource = ontClassImpl.removeAll(p);
//		mdrDatabase.commitend();
//		return resource;
//	}
//
//	@Override
//	public Resource removeProperties() {
//		return ontClassImpl.removeProperties();
//	}
//
//	@Override
//	public <T extends RDFNode> T as(Class<T> t) {
//		return ontClassImpl.as(t);
//	}
//
//	@Override
//	public Literal asLiteral() {
//		return ontClassImpl.asLiteral();
//	}
//
//	@Override
//	public Resource asResource() {
//		return ontClassImpl.asResource();
//	}
//
//	@Override
//	public <T extends RDFNode> boolean canAs(Class<T> t) {
//		return ontClassImpl.canAs(t);
//	}
//
//	@Override
//	public Model getModel() {
//		return ontClassImpl.getModel();
//	}
//
//	@Override
//	public boolean isAnon() {
//		return ontClassImpl.isAnon();
//	}
//
//	@Override
//	public boolean isLiteral() {
//		return ontClassImpl.isLiteral();
//	}
//
//	@Override
//	public boolean isResource() {
//		return ontClassImpl.isResource();
//	}
//
//	@Override
//	public boolean isURIResource() {
//		return ontClassImpl.isURIResource();
//	}
//
//	@Override
//	public Object visitWith(RDFVisitor rv) {
//		return ontClassImpl.visitWith(rv);
//	}
//
//	@Override
//	public Node asNode() {
//		return ontClassImpl.asNode();
//	}

}
