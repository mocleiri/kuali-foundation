package org.kuali.rice.kim.impl.identity.personal

import java.text.SimpleDateFormat
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.Transient
import org.kuali.rice.kim.api.KimConstants
import org.kuali.rice.kim.api.identity.personal.EntityBioDemographics
import org.kuali.rice.kim.api.identity.personal.EntityBioDemographicsContract
import org.kuali.rice.kim.api.identity.privacy.EntityPrivacyPreferences
import org.kuali.rice.kim.api.services.KimApiServiceLocator
import org.kuali.rice.krad.bo.PersistableBusinessObjectBase

@Entity
@Table(name = "KRIM_ENTITY_BIO_T")
class EntityBioDemographicsBo extends PersistableBusinessObjectBase implements EntityBioDemographicsContract {
    private static final long serialVersionUID = 6317317790920881093L;

    @Id
    @Column(name = "ENTITY_ID")
    String entityId;

    @Column(name = "BIRTH_DT")
    Date birthDateValue;

    @Column(name = "GNDR_CD")
    String genderCode;

    @Column(name = "DECEASED_DT")
    Date deceasedDateValue;

    @Column(name = "MARITAL_STATUS")
    String maritalStatusCode;

    @Column(name = "PRIM_LANG_CD")
    String primaryLanguageCode;

    @Column(name = "SEC_LANG_CD")
    String secondaryLanguageCode;

    @Column(name = "BIRTH_CNTRY_CD")
    String countryOfBirthCode;

    @Column(name = "BIRTH_STATE_CD")
    String birthStateCode;

    @Column(name = "BIRTH_CITY")
    String cityOfBirth;

    @Column(name = "GEO_ORIGIN")
    String geographicOrigin;

    @Transient
    boolean suppressPersonal;

  /*
   * Converts a mutable EntityBioDemographicsBo to an immutable EntityBioDemographics representation.
   * @param bo
   * @return an immutable EntityBioDemographics
   */
  static EntityBioDemographics to(EntityBioDemographicsBo bo) {
    if (bo == null) { return null }
    return EntityBioDemographics.Builder.create(bo).build()
  }

  /**
   * Creates a EntityBioDemographicsBo business object from an immutable representation of a EntityBioDemographics.
   * @param an immutable EntityBioDemographics
   * @return a EntityBioDemographicsBo
   */
  static EntityBioDemographicsBo from(EntityBioDemographics immutable) {
    if (immutable == null) {return null}

    EntityBioDemographicsBo bo = new EntityBioDemographicsBo()
    bo.entityId = immutable.entityId
    if (immutable.birthDateUnmasked != null) {
        bo.birthDateValue = new SimpleDateFormat("yyyy-MM-dd").parse(immutable.birthDateUnmasked)
    }
    bo.birthStateCode = immutable.birthStateCodeUnmasked
    bo.cityOfBirth = immutable.cityOfBirthUnmasked
    bo.countryOfBirthCode = immutable.countryOfBirthCodeUnmasked
    if (immutable.deceasedDate != null) {
        bo.deceasedDateValue = new SimpleDateFormat("yyyy-MM-dd").parse(immutable.deceasedDate)
    }
    bo.genderCode = immutable.genderCodeUnmasked
    bo.geographicOrigin = immutable.geographicOriginUnmasked
    bo.maritalStatusCode = immutable.maritalStatusCodeUnmasked
    bo.primaryLanguageCode = immutable.primaryLanguageCodeUnmasked
    bo.secondaryLanguageCode = immutable.secondaryLanguageCodeUnmasked
    bo.versionNumber = immutable.versionNumber
    bo.objectId = immutable.objectId

    return bo;
  }

    @Override
    String getBirthDate() {
        if (this.birthDateValue != null) {
            if (isSuppressPersonal()) {
                return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK
            }
            return new SimpleDateFormat("yyyy-MM-dd").format(this.birthDateValue)
        }
        return null;
    }

    @Override
    String getDeceasedDate() {
        if (this.deceasedDateValue != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(this.deceasedDateValue)
        }
        return null
    }

    @Override
    String getBirthDateUnmasked() {
        if (this.birthDateValue != null) {
            return new SimpleDateFormat("yyyy-MM-dd").format(this.birthDateValue)
        }
        return null;
    }

    @Override
    boolean isSuppressPersonal() {
        if (this.suppressPersonal == null) {
            EntityPrivacyPreferences privacy = KimApiServiceLocator.getIdentityService().getEntityPrivacyPreferences(getEntityId())
            if (privacy != null) {
               this.suppressPersonal = privacy.isSuppressPersonal()
            } else {
                this.suppressPersonal = false
            }
        }

        return suppressPersonal;
    }

    String getGenderCode() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.genderCode
    }

    String getMaritalStatusCode() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.maritalStatusCode
    }
    String getPrimaryLanguageCode() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.primaryLanguageCode
    }
    String getSecondaryLanguageCode() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.secondaryLanguageCode
    }
    String getCountryOfBirthCode() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.countryOfBirthCode
    }
    String getBirthStateCode() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.birthStateCode
    }
    String getCityOfBirth() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.cityOfBirth
    }
    String getGeographicOrigin() {
        if (isSuppressPersonal()) {
            return KimConstants.RestrictedMasks.RESTRICTED_DATA_MASK_CODE
        }
        return this.geographicOrigin
    }


    String getGenderCodeUnmasked() {
        return this.genderCode
    }
    String getMaritalStatusCodeUnmasked() {
        return this.maritalStatusCode
    }
    String getPrimaryLanguageCodeUnmasked() {
        return this.primaryLanguageCode
    }
    String getSecondaryLanguageCodeUnmasked() {
        return this.secondaryLanguageCode
    }
    String getCountryOfBirthCodeUnmasked() {
        return this.countryOfBirthCode
    }
    String getBirthStateCodeUnmasked() {
        return this.birthStateCode
    }
    String getCityOfBirthUnmasked() {
        return this.cityOfBirth
    }
    String getGeographicOriginUnmasked() {
        return this.geographicOrigin
    }
}
