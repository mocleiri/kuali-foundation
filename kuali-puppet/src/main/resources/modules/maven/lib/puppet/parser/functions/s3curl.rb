require 'rubygems'
require 'fog'
require 'time'

module Puppet::Parser::Functions
  newfunction(:s3curl, :type => :rvalue, :doc => <<-EOS
    Generates a cURL command containing a presigned url for the S3 object specified
    by the bucket and key.  Executing the cURL command returned by this function
    will download the S3 object to filename
    EOS
             ) do |args|
    bucket   = args[0] # S3 bucket name
    key      = args[1] # key for an object in the bucket
    filename = args[2] # path on the local file system to download the S3 object to
    expires  = args[3] # length of time (in seconds) the url is valid for
    Fog.credentials_path = '/etc/puppet/fog_cred'
    s3 = Fog::Storage.new(:provider => 'AWS')
    s3bucket = s3.directories.get(bucket)
    url = s3bucket.files.get_https_url(key, Time.parse(DateTime.now.to_s).to_i + expires.to_i)
    return "curl --create-dirs --retry 3 --silent --output #{filename} '#{url}'"
  end
end
