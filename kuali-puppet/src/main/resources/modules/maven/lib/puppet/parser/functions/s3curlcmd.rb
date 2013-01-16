require 'rubygems'
require 'fog'
require 'time'

module Puppet::Parser::Functions
  newfunction(:s3curlcmd, :type => :rvalue, :doc => <<-EOS
    Generates a curl command containing a presigned url for the s3 object specified
    by the bucket and key.  The contents of the url are downloaded to filename.
    EOS
             ) do |args|
    bucket   = args[0] # S3 bucket name
    key      = args[1] # key into that bucket
    filename = args[2] # local file where the file will be downloaded
    expires  = args[3] # in seconds from the current time
    Fog.credentials_path = '/etc/puppet/fog_cred'
    s3 = Fog::Storage.new(:provider => 'AWS')
    s3bucket = s3.directories.get(bucket)
    url = s3bucket.files.get_https_url(key, Time.parse(DateTime.now.to_s).to_i + expires.to_i)
    return "curl --create-dirs --retry 3 --silent --output #{filename} '#{url}'"
  end
end
