package best.wecode.awsimageupload.buckets;

public enum BucketName {
    PROFILE_IMAGE("wecode-image-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
