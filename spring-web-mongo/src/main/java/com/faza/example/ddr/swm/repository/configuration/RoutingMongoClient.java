package com.faza.example.ddr.swm.repository.configuration;

import com.mongodb.ClientBulkWriteException;
import com.mongodb.ClientSessionOptions;
import com.mongodb.ReadConcern;
import com.mongodb.ReadPreference;
import com.mongodb.WriteConcern;
import com.mongodb.client.ChangeStreamIterable;
import com.mongodb.client.ClientSession;
import com.mongodb.client.ListDatabasesIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCluster;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.bulk.ClientBulkWriteOptions;
import com.mongodb.client.model.bulk.ClientBulkWriteResult;
import com.mongodb.client.model.bulk.ClientNamespacedWriteModel;
import com.mongodb.connection.ClusterDescription;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

public abstract class RoutingMongoClient implements MongoClient {

    private final String defaultRouteName;
    private final Map<String, MongoClient> mongoClients;

    public RoutingMongoClient(String defaultRouteName, Map<String, MongoClient> mongoClients) {
        this.defaultRouteName = defaultRouteName;
        this.mongoClients = mongoClients;
    }

    @Override
    public void close() {
        mongoClients.values().forEach(MongoClient::close);
    }

    @Override
    public ClusterDescription getClusterDescription() {
        return this.getMongoClient().getClusterDescription();
    }

    @Override
    public CodecRegistry getCodecRegistry() {
        return this.getMongoClient().getCodecRegistry();
    }

    @Override
    public ReadPreference getReadPreference() {
        return this.getMongoClient().getReadPreference();
    }

    @Override
    public WriteConcern getWriteConcern() {
        return this.getMongoClient().getWriteConcern();
    }

    @Override
    public ReadConcern getReadConcern() {
        return this.getMongoClient().getReadConcern();
    }

    @Override
    public Long getTimeout(TimeUnit timeUnit) {
        return this.getMongoClient().getTimeout(timeUnit);
    }

    @Override
    public MongoCluster withCodecRegistry(CodecRegistry codecRegistry) {
        return this.getMongoClient().withCodecRegistry(codecRegistry);
    }

    @Override
    public MongoCluster withReadPreference(ReadPreference readPreference) {
        return this.getMongoClient().withReadPreference(readPreference);
    }

    @Override
    public MongoCluster withWriteConcern(WriteConcern writeConcern) {
        return this.getMongoClient().withWriteConcern(writeConcern);
    }

    @Override
    public MongoCluster withReadConcern(ReadConcern readConcern) {
        return this.getMongoClient().withReadConcern(readConcern);
    }

    @Override
    public MongoCluster withTimeout(long timeout, TimeUnit timeUnit) {
        return this.getMongoClient().withTimeout(timeout, timeUnit);
    }

    @Override
    public MongoDatabase getDatabase(String databaseName) {
        return this.getMongoClient().getDatabase(databaseName);
    }

    @Override
    public ClientSession startSession() {
        return this.getMongoClient().startSession();
    }

    @Override
    public ClientSession startSession(ClientSessionOptions options) {
        return this.getMongoClient().startSession(options);
    }

    @Override
    public MongoIterable<String> listDatabaseNames() {
        return this.getMongoClient().listDatabaseNames();
    }

    @Override
    public MongoIterable<String> listDatabaseNames(ClientSession clientSession) {
        return this.getMongoClient().listDatabaseNames(clientSession);
    }

    @Override
    public ListDatabasesIterable<Document> listDatabases() {
        return this.getMongoClient().listDatabases();
    }

    @Override
    public ListDatabasesIterable<Document> listDatabases(ClientSession clientSession) {
        return this.getMongoClient().listDatabases(clientSession);
    }

    @Override
    public <TResult> ListDatabasesIterable<TResult> listDatabases(Class<TResult> tResultClass) {
        return this.getMongoClient().listDatabases(tResultClass);
    }

    @Override
    public <TResult> ListDatabasesIterable<TResult> listDatabases(ClientSession clientSession,
        Class<TResult> tResultClass) {
        return this.getMongoClient().listDatabases(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch() {
        return this.getMongoClient().watch();
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(Class<TResult> tResultClass) {
        return this.getMongoClient().watch(tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(List<? extends Bson> pipeline) {
        return this.getMongoClient().watch(pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(List<? extends Bson> pipeline,
        Class<TResult> tResultClass) {
        return this.getMongoClient().watch(pipeline, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession) {
        return this.getMongoClient().watch(clientSession);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession,
        Class<TResult> tResultClass) {
        return this.getMongoClient().watch(clientSession, tResultClass);
    }

    @Override
    public ChangeStreamIterable<Document> watch(ClientSession clientSession,
        List<? extends Bson> pipeline) {
        return this.getMongoClient().watch(clientSession, pipeline);
    }

    @Override
    public <TResult> ChangeStreamIterable<TResult> watch(ClientSession clientSession,
        List<? extends Bson> pipeline,
        Class<TResult> tResultClass) {
        return this.getMongoClient().watch(clientSession, pipeline, tResultClass);
    }

    @Override
    public ClientBulkWriteResult bulkWrite(List<? extends ClientNamespacedWriteModel> models)
        throws ClientBulkWriteException {
        return this.getMongoClient().bulkWrite(models);
    }

    @Override
    public ClientBulkWriteResult bulkWrite(List<? extends ClientNamespacedWriteModel> models,
        ClientBulkWriteOptions options) throws ClientBulkWriteException {
        return this.getMongoClient().bulkWrite(models, options);
    }

    @Override
    public ClientBulkWriteResult bulkWrite(ClientSession clientSession,
        List<? extends ClientNamespacedWriteModel> models) throws ClientBulkWriteException {
        return this.getMongoClient().bulkWrite(clientSession, models);
    }

    @Override
    public ClientBulkWriteResult bulkWrite(ClientSession clientSession,
        List<? extends ClientNamespacedWriteModel> models,
        ClientBulkWriteOptions options) throws ClientBulkWriteException {
        return this.getMongoClient().bulkWrite(clientSession, models, options);
    }

    private MongoClient getMongoClient() {
        String routeName = getRouteName();
        if (routeName == null) {
            routeName = defaultRouteName;
        }
        return mongoClients.get(routeName);
    }

    public abstract String getRouteName();
}
