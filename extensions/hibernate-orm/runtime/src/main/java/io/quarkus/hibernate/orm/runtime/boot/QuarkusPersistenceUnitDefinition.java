package io.quarkus.hibernate.orm.runtime.boot;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.persistence.spi.PersistenceUnitTransactionType;

import org.hibernate.jpa.boot.spi.PersistenceUnitDescriptor;

import io.quarkus.hibernate.orm.runtime.boot.xml.RecordableXmlMapping;
import io.quarkus.hibernate.orm.runtime.integration.HibernateOrmIntegrationStaticDescriptor;
import io.quarkus.hibernate.orm.runtime.migration.MultiTenancyStrategy;
import io.quarkus.runtime.ObjectSubstitution;

/**
 * This represent the fully specified configuration of a Persistence Unit,
 * in a format which is compatible with the bytecode recorder.
 */
public final class QuarkusPersistenceUnitDefinition {

    private final LightPersistenceXmlDescriptor actualHibernateDescriptor;
    private final Optional<String> dataSource;
    private final MultiTenancyStrategy multitenancyStrategy;
    private final List<RecordableXmlMapping> xmlMappings;
    private final boolean isReactive;
    private final boolean fromPersistenceXml;
    private final List<HibernateOrmIntegrationStaticDescriptor> integrationStaticDescriptors;
    private final Map<String, String> quarkusConfigUnsupportedProperties;

    public QuarkusPersistenceUnitDefinition(PersistenceUnitDescriptor persistenceUnitDescriptor, Optional<String> dataSource,
            MultiTenancyStrategy multitenancyStrategy, List<RecordableXmlMapping> xmlMappings,
            Map<String, String> quarkusConfigUnsupportedProperties,
            boolean isReactive, boolean fromPersistenceXml,
            List<HibernateOrmIntegrationStaticDescriptor> integrationStaticDescriptors) {
        Objects.requireNonNull(persistenceUnitDescriptor);
        Objects.requireNonNull(multitenancyStrategy);
        this.actualHibernateDescriptor = LightPersistenceXmlDescriptor.validateAndReadFrom(persistenceUnitDescriptor);
        this.dataSource = dataSource;
        this.multitenancyStrategy = multitenancyStrategy;
        this.xmlMappings = xmlMappings;
        this.quarkusConfigUnsupportedProperties = quarkusConfigUnsupportedProperties;
        this.isReactive = isReactive;
        this.fromPersistenceXml = fromPersistenceXml;
        this.integrationStaticDescriptors = integrationStaticDescriptors;
    }

    /**
     * For bytecode deserialization
     */
    private QuarkusPersistenceUnitDefinition(LightPersistenceXmlDescriptor persistenceUnitDescriptor,
            Optional<String> dataSource,
            MultiTenancyStrategy multitenancyStrategy,
            List<RecordableXmlMapping> xmlMappings,
            Map<String, String> quarkusConfigUnsupportedProperties,
            boolean isReactive,
            boolean fromPersistenceXml,
            List<HibernateOrmIntegrationStaticDescriptor> integrationStaticDescriptors) {
        Objects.requireNonNull(persistenceUnitDescriptor);
        Objects.requireNonNull(dataSource);
        Objects.requireNonNull(multitenancyStrategy);
        this.actualHibernateDescriptor = persistenceUnitDescriptor;
        this.dataSource = dataSource;
        this.multitenancyStrategy = multitenancyStrategy;
        this.xmlMappings = xmlMappings;
        this.quarkusConfigUnsupportedProperties = quarkusConfigUnsupportedProperties;
        this.isReactive = isReactive;
        this.fromPersistenceXml = fromPersistenceXml;
        this.integrationStaticDescriptors = integrationStaticDescriptors;
    }

    public PersistenceUnitDescriptor getActualHibernateDescriptor() {
        return actualHibernateDescriptor;
    }

    public String getName() {
        return actualHibernateDescriptor.getName();
    }

    public Optional<String> getDataSource() {
        return dataSource;
    }

    public MultiTenancyStrategy getMultitenancyStrategy() {
        return multitenancyStrategy;
    }

    public List<RecordableXmlMapping> getXmlMappings() {
        return xmlMappings;
    }

    //TODO assert that we match the right type of ORM!
    public boolean isReactive() {
        return isReactive;
    }

    public boolean isFromPersistenceXml() {
        return fromPersistenceXml;
    }

    public List<HibernateOrmIntegrationStaticDescriptor> getIntegrationStaticDescriptors() {
        return integrationStaticDescriptors;
    }

    public Map<String, String> getQuarkusConfigUnsupportedProperties() {
        return quarkusConfigUnsupportedProperties;
    }

    /**
     * This includes the state of both the QuarkusPersistenceUnitDefinition
     * and its more complex field of type LightPersistenceXmlDescriptor
     */
    public static class Serialized {

        private Optional<String> dataSource;
        private MultiTenancyStrategy multitenancyStrategy;
        private List<RecordableXmlMapping> xmlMappingBindings;
        private boolean isReactive;
        private boolean fromPersistenceXml;
        private String puName;
        private String puProviderClassName;
        private boolean puUseQuotedIdentifiers;
        private PersistenceUnitTransactionType puTransactionType;
        private ValidationMode puValidationMode;
        private SharedCacheMode puSharedCachemode;
        private List<String> puManagedClassNames;
        private Properties puProperties;
        private List<HibernateOrmIntegrationStaticDescriptor> integrationStaticDescriptors;
        private Map<String, String> quarkusConfigUnsupportedProperties;

        //All standard getters and setters generated by IDE:

        public Optional<String> getDataSource() {
            return dataSource;
        }

        public void setDataSource(Optional<String> dataSource) {
            this.dataSource = dataSource;
        }

        public String getPuName() {
            return puName;
        }

        public void setPuName(String puName) {
            this.puName = puName;
        }

        public MultiTenancyStrategy getMultitenancyStrategy() {
            return multitenancyStrategy;
        }

        public void setMultitenancyStrategy(MultiTenancyStrategy multitenancyStrategy) {
            this.multitenancyStrategy = multitenancyStrategy;
        }

        public List<RecordableXmlMapping> getXmlMappingBindings() {
            return xmlMappingBindings;
        }

        public void setXmlMappingBindings(List<RecordableXmlMapping> xmlMappingBindings) {
            this.xmlMappingBindings = xmlMappingBindings;
        }

        public boolean isReactive() {
            return isReactive;
        }

        public void setReactive(boolean reactive) {
            isReactive = reactive;
        }

        public boolean isFromPersistenceXml() {
            return fromPersistenceXml;
        }

        public void setFromPersistenceXml(boolean fromPersistenceXml) {
            this.fromPersistenceXml = fromPersistenceXml;
        }

        public String getPuProviderClassName() {
            return puProviderClassName;
        }

        public void setPuProviderClassName(String puProviderClassName) {
            this.puProviderClassName = puProviderClassName;
        }

        public boolean isPuUseQuotedIdentifiers() {
            return puUseQuotedIdentifiers;
        }

        public void setPuUseQuotedIdentifiers(boolean puUseQuotedIdentifiers) {
            this.puUseQuotedIdentifiers = puUseQuotedIdentifiers;
        }

        public PersistenceUnitTransactionType getPuTransactionType() {
            return puTransactionType;
        }

        public void setPuTransactionType(PersistenceUnitTransactionType puTransactionType) {
            this.puTransactionType = puTransactionType;
        }

        public ValidationMode getPuValidationMode() {
            return puValidationMode;
        }

        public void setPuValidationMode(ValidationMode puValidationMode) {
            this.puValidationMode = puValidationMode;
        }

        public SharedCacheMode getPuSharedCachemode() {
            return puSharedCachemode;
        }

        public void setPuSharedCachemode(SharedCacheMode puSharedCachemode) {
            this.puSharedCachemode = puSharedCachemode;
        }

        public List<String> getPuManagedClassNames() {
            return puManagedClassNames;
        }

        public void setPuManagedClassNames(List<String> puManagedClassNames) {
            this.puManagedClassNames = puManagedClassNames;
        }

        public Properties getPuProperties() {
            return puProperties;
        }

        public void setPuProperties(Properties puProperties) {
            this.puProperties = puProperties;
        }

        public List<HibernateOrmIntegrationStaticDescriptor> getIntegrationStaticDescriptors() {
            return integrationStaticDescriptors;
        }

        public void setIntegrationStaticDescriptors(
                List<HibernateOrmIntegrationStaticDescriptor> integrationStaticDescriptors) {
            this.integrationStaticDescriptors = integrationStaticDescriptors;
        }

        public Map<String, String> getQuarkusConfigUnsupportedProperties() {
            return quarkusConfigUnsupportedProperties;
        }

        public void setQuarkusConfigUnsupportedProperties(Map<String, String> quarkusConfigUnsupportedProperties) {
            this.quarkusConfigUnsupportedProperties = quarkusConfigUnsupportedProperties;
        }
    }

    public static final class Substitution implements ObjectSubstitution<QuarkusPersistenceUnitDefinition, Serialized> {

        @Override
        public Serialized serialize(final QuarkusPersistenceUnitDefinition obj) {
            final Serialized s = new Serialized();
            //First, fields from LightPersistenceXmlDescriptor:
            s.setPuName(obj.actualHibernateDescriptor.getName());
            s.setPuProviderClassName(obj.actualHibernateDescriptor.getProviderClassName());
            s.setPuUseQuotedIdentifiers(obj.actualHibernateDescriptor.isUseQuotedIdentifiers());
            s.setPuTransactionType(obj.actualHibernateDescriptor.getTransactionType());
            s.setPuValidationMode(obj.actualHibernateDescriptor.getValidationMode());
            s.setPuSharedCachemode(obj.actualHibernateDescriptor.getSharedCacheMode());
            s.setPuManagedClassNames(obj.actualHibernateDescriptor.getManagedClassNames());
            s.setPuProperties(obj.actualHibernateDescriptor.getProperties());
            //Remaining fields of QuarkusPersistenceUnitDefinition
            s.setDataSource(obj.getDataSource());
            s.setMultitenancyStrategy(obj.getMultitenancyStrategy());
            s.setXmlMappingBindings(obj.getXmlMappings());
            s.setQuarkusConfigUnsupportedProperties(obj.getQuarkusConfigUnsupportedProperties());
            s.setReactive(obj.isReactive);
            s.setFromPersistenceXml(obj.isFromPersistenceXml());
            s.setIntegrationStaticDescriptors(obj.getIntegrationStaticDescriptors());
            return s;
        }

        @Override
        public QuarkusPersistenceUnitDefinition deserialize(Serialized obj) {
            LightPersistenceXmlDescriptor xmlDescriptor = new LightPersistenceXmlDescriptor(
                    obj.puName, obj.puProviderClassName, obj.puUseQuotedIdentifiers, obj.puTransactionType,
                    obj.puValidationMode, obj.puSharedCachemode, obj.puManagedClassNames, obj.puProperties);

            return new QuarkusPersistenceUnitDefinition(xmlDescriptor, obj.getDataSource(), obj.getMultitenancyStrategy(),
                    obj.getXmlMappingBindings(), obj.getQuarkusConfigUnsupportedProperties(),
                    obj.isReactive(), obj.isFromPersistenceXml(),
                    obj.getIntegrationStaticDescriptors());
        }
    }

}
