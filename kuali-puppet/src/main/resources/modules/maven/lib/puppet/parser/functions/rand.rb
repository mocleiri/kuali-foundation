module Puppet::Parser::Functions
  newfunction(:rand, :type => :rvalue) do |args|
    rand(vals.empty? ? 0 : args[0])
  end
end