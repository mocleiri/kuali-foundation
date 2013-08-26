/**
 * Model POJO's for database constructs
 */
@XmlAccessorType(javax.xml.bind.annotation.XmlAccessType.FIELD)
// JAXB's default is UNDEFINED.  This annotation makes the XML marshalling deterministic
// The Schema object uses @XmlType + propOrder to override alphabetical for the top level database objects
@XmlAccessorOrder(javax.xml.bind.annotation.XmlAccessOrder.ALPHABETICAL)
package org.kuali.common.impex.pojo;

import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;

