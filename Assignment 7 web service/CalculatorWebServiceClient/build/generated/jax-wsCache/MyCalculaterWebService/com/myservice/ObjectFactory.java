
package com.myservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.myservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Addition_QNAME = new QName("http://myservice.com/", "addition");
    private final static QName _AdditionResponse_QNAME = new QName("http://myservice.com/", "additionResponse");
    private final static QName _Division_QNAME = new QName("http://myservice.com/", "division");
    private final static QName _DivisionResponse_QNAME = new QName("http://myservice.com/", "divisionResponse");
    private final static QName _Multi_QNAME = new QName("http://myservice.com/", "multi");
    private final static QName _MultiResponse_QNAME = new QName("http://myservice.com/", "multiResponse");
    private final static QName _Subraction_QNAME = new QName("http://myservice.com/", "subraction");
    private final static QName _SubractionResponse_QNAME = new QName("http://myservice.com/", "subractionResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.myservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Addition }
     * 
     */
    public Addition createAddition() {
        return new Addition();
    }

    /**
     * Create an instance of {@link AdditionResponse }
     * 
     */
    public AdditionResponse createAdditionResponse() {
        return new AdditionResponse();
    }

    /**
     * Create an instance of {@link Division }
     * 
     */
    public Division createDivision() {
        return new Division();
    }

    /**
     * Create an instance of {@link DivisionResponse }
     * 
     */
    public DivisionResponse createDivisionResponse() {
        return new DivisionResponse();
    }

    /**
     * Create an instance of {@link Multi }
     * 
     */
    public Multi createMulti() {
        return new Multi();
    }

    /**
     * Create an instance of {@link MultiResponse }
     * 
     */
    public MultiResponse createMultiResponse() {
        return new MultiResponse();
    }

    /**
     * Create an instance of {@link Subraction }
     * 
     */
    public Subraction createSubraction() {
        return new Subraction();
    }

    /**
     * Create an instance of {@link SubractionResponse }
     * 
     */
    public SubractionResponse createSubractionResponse() {
        return new SubractionResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Addition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "addition")
    public JAXBElement<Addition> createAddition(Addition value) {
        return new JAXBElement<Addition>(_Addition_QNAME, Addition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AdditionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "additionResponse")
    public JAXBElement<AdditionResponse> createAdditionResponse(AdditionResponse value) {
        return new JAXBElement<AdditionResponse>(_AdditionResponse_QNAME, AdditionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Division }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "division")
    public JAXBElement<Division> createDivision(Division value) {
        return new JAXBElement<Division>(_Division_QNAME, Division.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DivisionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "divisionResponse")
    public JAXBElement<DivisionResponse> createDivisionResponse(DivisionResponse value) {
        return new JAXBElement<DivisionResponse>(_DivisionResponse_QNAME, DivisionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Multi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "multi")
    public JAXBElement<Multi> createMulti(Multi value) {
        return new JAXBElement<Multi>(_Multi_QNAME, Multi.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MultiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "multiResponse")
    public JAXBElement<MultiResponse> createMultiResponse(MultiResponse value) {
        return new JAXBElement<MultiResponse>(_MultiResponse_QNAME, MultiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Subraction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "subraction")
    public JAXBElement<Subraction> createSubraction(Subraction value) {
        return new JAXBElement<Subraction>(_Subraction_QNAME, Subraction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SubractionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://myservice.com/", name = "subractionResponse")
    public JAXBElement<SubractionResponse> createSubractionResponse(SubractionResponse value) {
        return new JAXBElement<SubractionResponse>(_SubractionResponse_QNAME, SubractionResponse.class, null, value);
    }

}
